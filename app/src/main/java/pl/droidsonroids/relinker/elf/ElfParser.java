package pl.droidsonroids.relinker.elf;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.droidsonroids.relinker.elf.Elf;

/* loaded from: classes.dex */
public class ElfParser implements Closeable, Elf {
    private final int MAGIC = 1179403647;
    private final FileChannel channel;

    public ElfParser(File file) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        FileInputStream inputStream = new FileInputStream(file);
        this.channel = inputStream.getChannel();
    }

    public Elf.Header parseHeader() throws IOException {
        this.channel.position(0L);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        if (readWord(buffer, 0L) == 1179403647) {
            short fileClass = readByte(buffer, 4L);
            boolean bigEndian = readByte(buffer, 5L) == 2;
            if (fileClass == 1) {
                return new Elf32Header(bigEndian, this);
            }
            if (fileClass == 2) {
                return new Elf64Header(bigEndian, this);
            }
            throw new IllegalStateException("Invalid class type!");
        }
        throw new IllegalArgumentException("Invalid ELF Magic!");
    }

    public List<String> parseNeededDependencies() throws IOException {
        long numProgramHeaderEntries;
        long i;
        long vStringTableOff;
        this.channel.position(0L);
        List<String> dependencies = new ArrayList<>();
        Elf.Header header = parseHeader();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.order(header.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long numProgramHeaderEntries2 = header.phnum;
        if (numProgramHeaderEntries2 == 65535) {
            Elf.SectionHeader sectionHeader = header.getSectionHeader(0);
            numProgramHeaderEntries = sectionHeader.info;
        } else {
            numProgramHeaderEntries = numProgramHeaderEntries2;
        }
        long i2 = 0;
        while (true) {
            if (i2 >= numProgramHeaderEntries) {
                i = 0;
                break;
            }
            Elf.ProgramHeader programHeader = header.getProgramHeader(i2);
            if (programHeader.type == 2) {
                long dynamicSectionOff = programHeader.offset;
                i = dynamicSectionOff;
                break;
            }
            i2++;
        }
        if (i == 0) {
            return Collections.unmodifiableList(dependencies);
        }
        int i3 = 0;
        List<Long> neededOffsets = new ArrayList<>();
        long vStringTableOff2 = 0;
        while (true) {
            Elf.DynamicStructure dynStructure = header.getDynamicStructure(i, i3);
            vStringTableOff = vStringTableOff2;
            long vStringTableOff3 = dynStructure.tag;
            if (vStringTableOff3 == 1) {
                neededOffsets.add(Long.valueOf(dynStructure.val));
            } else if (dynStructure.tag == 5) {
                vStringTableOff = dynStructure.val;
            }
            int i4 = i3 + 1;
            if (dynStructure.tag == 0) {
                break;
            }
            vStringTableOff2 = vStringTableOff;
            i3 = i4;
        }
        if (vStringTableOff != 0) {
            long stringTableOff = offsetFromVma(header, numProgramHeaderEntries, vStringTableOff);
            for (Long strOff : neededOffsets) {
                dependencies.add(readString(buffer, strOff.longValue() + stringTableOff));
            }
            return dependencies;
        }
        throw new IllegalStateException("String table offset not found!");
    }

    private long offsetFromVma(Elf.Header header, long numEntries, long vma) throws IOException {
        for (long i = 0; i < numEntries; i++) {
            Elf.ProgramHeader programHeader = header.getProgramHeader(i);
            if (programHeader.type == 1 && programHeader.vaddr <= vma && vma <= programHeader.vaddr + programHeader.memsz) {
                return (vma - programHeader.vaddr) + programHeader.offset;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.channel.close();
    }

    protected String readString(ByteBuffer buffer, long offset) throws IOException {
        StringBuilder builder = new StringBuilder();
        while (true) {
            long offset2 = 1 + offset;
            short c = readByte(buffer, offset);
            if (c == 0) {
                return builder.toString();
            }
            builder.append((char) c);
            offset = offset2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long readLong(ByteBuffer buffer, long offset) throws IOException {
        read(buffer, offset, 8);
        return buffer.getLong();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long readWord(ByteBuffer buffer, long offset) throws IOException {
        read(buffer, offset, 4);
        return buffer.getInt() & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int readHalf(ByteBuffer buffer, long offset) throws IOException {
        read(buffer, offset, 2);
        return buffer.getShort() & 65535;
    }

    protected short readByte(ByteBuffer buffer, long offset) throws IOException {
        read(buffer, offset, 1);
        return (short) (buffer.get() & 255);
    }

    protected void read(ByteBuffer buffer, long offset, int length) throws IOException {
        buffer.position(0);
        buffer.limit(length);
        long bytesRead = 0;
        while (bytesRead < length) {
            int read = this.channel.read(buffer, offset + bytesRead);
            if (read != -1) {
                bytesRead += read;
            } else {
                throw new EOFException();
            }
        }
        buffer.position(0);
    }
}
