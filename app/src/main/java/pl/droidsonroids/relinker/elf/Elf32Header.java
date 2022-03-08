package pl.droidsonroids.relinker.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;

/* loaded from: classes.dex */
public class Elf32Header extends Elf.Header {
    private final ElfParser parser;

    public Elf32Header(boolean bigEndian, ElfParser parser) throws IOException {
        this.bigEndian = bigEndian;
        this.parser = parser;
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = parser.readHalf(buffer, 16L);
        this.phoff = parser.readWord(buffer, 28L);
        this.shoff = parser.readWord(buffer, 32L);
        this.phentsize = parser.readHalf(buffer, 42L);
        this.phnum = parser.readHalf(buffer, 44L);
        this.shentsize = parser.readHalf(buffer, 46L);
        this.shnum = parser.readHalf(buffer, 48L);
        this.shstrndx = parser.readHalf(buffer, 50L);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.SectionHeader getSectionHeader(int index) throws IOException {
        return new Section32Header(this.parser, this, index);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.ProgramHeader getProgramHeader(long index) throws IOException {
        return new Program32Header(this.parser, this, index);
    }

    @Override // pl.droidsonroids.relinker.elf.Elf.Header
    public Elf.DynamicStructure getDynamicStructure(long baseOffset, int index) throws IOException {
        return new Dynamic32Structure(this.parser, this, baseOffset, index);
    }
}
