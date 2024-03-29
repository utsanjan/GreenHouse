package com.google.firebase.database.core.view;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.WriteTreeRef;
import com.google.firebase.database.core.operation.AckUserWrite;
import com.google.firebase.database.core.operation.Merge;
import com.google.firebase.database.core.operation.Operation;
import com.google.firebase.database.core.operation.Overwrite;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.view.filter.ChildChangeAccumulator;
import com.google.firebase.database.core.view.filter.NodeFilter;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.ChildrenNode;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.Index;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.KeyIndex;
import com.google.firebase.database.snapshot.NamedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class ViewProcessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static NodeFilter.CompleteChildSource NO_COMPLETE_SOURCE = new NodeFilter.CompleteChildSource() { // from class: com.google.firebase.database.core.view.ViewProcessor.1
        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public Node getCompleteChild(ChildKey childKey) {
            return null;
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public NamedNode getChildAfterChild(Index index, NamedNode child, boolean reverse) {
            return null;
        }
    };
    private final NodeFilter filter;

    public ViewProcessor(NodeFilter filter) {
        this.filter = filter;
    }

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    public static class ProcessorResult {
        public final List<Change> changes;
        public final ViewCache viewCache;

        public ProcessorResult(ViewCache viewCache, List<Change> changes) {
            this.viewCache = viewCache;
            this.changes = changes;
        }
    }

    public ProcessorResult applyOperation(ViewCache oldViewCache, Operation operation, WriteTreeRef writesCache, Node optCompleteCache) {
        ViewCache newViewCache;
        ChildChangeAccumulator accumulator = new ChildChangeAccumulator();
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[operation.getType().ordinal()];
        if (i == 1) {
            Overwrite overwrite = (Overwrite) operation;
            if (overwrite.getSource().isFromUser()) {
                newViewCache = applyUserOverwrite(oldViewCache, overwrite.getPath(), overwrite.getSnapshot(), writesCache, optCompleteCache, accumulator);
            } else {
                boolean filterServerNode = overwrite.getSource().isTagged() || (oldViewCache.getServerCache().isFiltered() && !overwrite.getPath().isEmpty());
                newViewCache = applyServerOverwrite(oldViewCache, overwrite.getPath(), overwrite.getSnapshot(), writesCache, optCompleteCache, filterServerNode, accumulator);
            }
        } else if (i == 2) {
            Merge merge = (Merge) operation;
            if (merge.getSource().isFromUser()) {
                newViewCache = applyUserMerge(oldViewCache, merge.getPath(), merge.getChildren(), writesCache, optCompleteCache, accumulator);
            } else {
                boolean filterServerNode2 = merge.getSource().isTagged() || oldViewCache.getServerCache().isFiltered();
                newViewCache = applyServerMerge(oldViewCache, merge.getPath(), merge.getChildren(), writesCache, optCompleteCache, filterServerNode2, accumulator);
            }
        } else if (i == 3) {
            AckUserWrite ackUserWrite = (AckUserWrite) operation;
            if (!ackUserWrite.isRevert()) {
                newViewCache = ackUserWrite(oldViewCache, ackUserWrite.getPath(), ackUserWrite.getAffectedTree(), writesCache, optCompleteCache, accumulator);
            } else {
                newViewCache = revertUserWrite(oldViewCache, ackUserWrite.getPath(), writesCache, optCompleteCache, accumulator);
            }
        } else if (i == 4) {
            newViewCache = listenComplete(oldViewCache, operation.getPath(), writesCache, optCompleteCache, accumulator);
        } else {
            throw new AssertionError("Unknown operation: " + operation.getType());
        }
        List<Change> changes = new ArrayList<>(accumulator.getChanges());
        maybeAddValueEvent(oldViewCache, newViewCache, changes);
        return new ProcessorResult(newViewCache, changes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* renamed from: com.google.firebase.database.core.view.ViewProcessor$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType;

        static {
            int[] iArr = new int[Operation.OperationType.values().length];
            $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType = iArr;
            try {
                iArr[Operation.OperationType.Overwrite.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.Merge.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.AckUserWrite.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$core$operation$Operation$OperationType[Operation.OperationType.ListenComplete.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private void maybeAddValueEvent(ViewCache oldViewCache, ViewCache newViewCache, List<Change> accumulator) {
        CacheNode eventSnap = newViewCache.getEventCache();
        if (eventSnap.isFullyInitialized()) {
            boolean isLeafOrEmpty = eventSnap.getNode().isLeafNode() || eventSnap.getNode().isEmpty();
            if (!accumulator.isEmpty() || !oldViewCache.getEventCache().isFullyInitialized() || ((isLeafOrEmpty && !eventSnap.getNode().equals(oldViewCache.getCompleteEventSnap())) || !eventSnap.getNode().getPriority().equals(oldViewCache.getCompleteEventSnap().getPriority()))) {
                accumulator.add(Change.valueChange(eventSnap.getIndexedNode()));
            }
        }
    }

    private ViewCache generateEventCacheAfterServerEvent(ViewCache viewCache, Path changePath, WriteTreeRef writesCache, NodeFilter.CompleteChildSource source, ChildChangeAccumulator accumulator) {
        IndexedNode newEventCache;
        Node serverNode;
        Node newEventChild;
        IndexedNode newEventCache2;
        Node serverCache;
        CacheNode oldEventSnap = viewCache.getEventCache();
        if (writesCache.shadowingWrite(changePath) != null) {
            return viewCache;
        }
        if (changePath.isEmpty()) {
            if (viewCache.getServerCache().isFiltered()) {
                Node serverCache2 = viewCache.getCompleteServerSnap();
                Node completeChildren = serverCache2 instanceof ChildrenNode ? serverCache2 : EmptyNode.Empty();
                serverCache = writesCache.calcCompleteEventChildren(completeChildren);
            } else {
                serverCache = writesCache.calcCompleteEventCache(viewCache.getCompleteServerSnap());
            }
            IndexedNode indexedNode = IndexedNode.from(serverCache, this.filter.getIndex());
            newEventCache = this.filter.updateFullNode(viewCache.getEventCache().getIndexedNode(), indexedNode, accumulator);
        } else {
            ChildKey childKey = changePath.getFront();
            if (childKey.isPriorityChildName()) {
                Node oldEventNode = oldEventSnap.getNode();
                Node updatedPriority = writesCache.calcEventCacheAfterServerOverwrite(changePath, oldEventNode, viewCache.getServerCache().getNode());
                if (updatedPriority != null) {
                    newEventCache2 = this.filter.updatePriority(oldEventSnap.getIndexedNode(), updatedPriority);
                } else {
                    newEventCache2 = oldEventSnap.getIndexedNode();
                }
                newEventCache = newEventCache2;
            } else {
                Path childChangePath = changePath.popFront();
                if (oldEventSnap.isCompleteForChild(childKey)) {
                    Node eventChildUpdate = writesCache.calcEventCacheAfterServerOverwrite(changePath, oldEventSnap.getNode(), viewCache.getServerCache().getNode());
                    if (eventChildUpdate != null) {
                        newEventChild = oldEventSnap.getNode().getImmediateChild(childKey).updateChild(childChangePath, eventChildUpdate);
                    } else {
                        Node newEventChild2 = oldEventSnap.getNode();
                        newEventChild = newEventChild2.getImmediateChild(childKey);
                    }
                    serverNode = newEventChild;
                } else {
                    serverNode = writesCache.calcCompleteChild(childKey, viewCache.getServerCache());
                }
                if (serverNode != null) {
                    newEventCache = this.filter.updateChild(oldEventSnap.getIndexedNode(), childKey, serverNode, childChangePath, source, accumulator);
                } else {
                    IndexedNode newEventCache3 = oldEventSnap.getIndexedNode();
                    newEventCache = newEventCache3;
                }
            }
        }
        return viewCache.updateEventSnap(newEventCache, oldEventSnap.isFullyInitialized() || changePath.isEmpty(), this.filter.filtersNodes());
    }

    private ViewCache applyServerOverwrite(ViewCache oldViewCache, Path changePath, Node changedSnap, WriteTreeRef writesCache, Node optCompleteCache, boolean filterServerNode, ChildChangeAccumulator accumulator) {
        IndexedNode newServerCache;
        CacheNode oldServerSnap = oldViewCache.getServerCache();
        NodeFilter serverFilter = this.filter;
        if (!filterServerNode) {
            serverFilter = serverFilter.getIndexedFilter();
        }
        boolean z = true;
        if (changePath.isEmpty()) {
            newServerCache = serverFilter.updateFullNode(oldServerSnap.getIndexedNode(), IndexedNode.from(changedSnap, serverFilter.getIndex()), null);
        } else if (!serverFilter.filtersNodes() || oldServerSnap.isFiltered()) {
            ChildKey childKey = changePath.getFront();
            if (!oldServerSnap.isCompleteForPath(changePath) && changePath.size() > 1) {
                return oldViewCache;
            }
            Path childChangePath = changePath.popFront();
            Node childNode = oldServerSnap.getNode().getImmediateChild(childKey);
            Node newChildNode = childNode.updateChild(childChangePath, changedSnap);
            if (childKey.isPriorityChildName()) {
                newServerCache = serverFilter.updatePriority(oldServerSnap.getIndexedNode(), newChildNode);
            } else {
                newServerCache = serverFilter.updateChild(oldServerSnap.getIndexedNode(), childKey, newChildNode, childChangePath, NO_COMPLETE_SOURCE, null);
            }
        } else {
            ChildKey childKey2 = changePath.getFront();
            Path updatePath = changePath.popFront();
            Node newChild = oldServerSnap.getNode().getImmediateChild(childKey2).updateChild(updatePath, changedSnap);
            IndexedNode newServerNode = oldServerSnap.getIndexedNode().updateChild(childKey2, newChild);
            IndexedNode newServerCache2 = serverFilter.updateFullNode(oldServerSnap.getIndexedNode(), newServerNode, null);
            newServerCache = newServerCache2;
        }
        if (!oldServerSnap.isFullyInitialized() && !changePath.isEmpty()) {
            z = false;
        }
        ViewCache newViewCache = oldViewCache.updateServerSnap(newServerCache, z, serverFilter.filtersNodes());
        NodeFilter.CompleteChildSource source = new WriteTreeCompleteChildSource(writesCache, newViewCache, optCompleteCache);
        return generateEventCacheAfterServerEvent(newViewCache, changePath, writesCache, source, accumulator);
    }

    private ViewCache applyUserOverwrite(ViewCache oldViewCache, Path changePath, Node changedSnap, WriteTreeRef writesCache, Node optCompleteCache, ChildChangeAccumulator accumulator) {
        Node newChild;
        CacheNode oldEventSnap = oldViewCache.getEventCache();
        NodeFilter.CompleteChildSource source = new WriteTreeCompleteChildSource(writesCache, oldViewCache, optCompleteCache);
        if (changePath.isEmpty()) {
            IndexedNode newIndexed = IndexedNode.from(changedSnap, this.filter.getIndex());
            IndexedNode newEventCache = this.filter.updateFullNode(oldViewCache.getEventCache().getIndexedNode(), newIndexed, accumulator);
            ViewCache newViewCache = oldViewCache.updateEventSnap(newEventCache, true, this.filter.filtersNodes());
            return newViewCache;
        }
        ChildKey childKey = changePath.getFront();
        if (childKey.isPriorityChildName()) {
            IndexedNode newEventCache2 = this.filter.updatePriority(oldViewCache.getEventCache().getIndexedNode(), changedSnap);
            ViewCache newViewCache2 = oldViewCache.updateEventSnap(newEventCache2, oldEventSnap.isFullyInitialized(), oldEventSnap.isFiltered());
            return newViewCache2;
        }
        Path childChangePath = changePath.popFront();
        Node oldChild = oldEventSnap.getNode().getImmediateChild(childKey);
        if (childChangePath.isEmpty()) {
            newChild = changedSnap;
        } else {
            Node childNode = source.getCompleteChild(childKey);
            if (childNode == null) {
                newChild = EmptyNode.Empty();
            } else if (!childChangePath.getBack().isPriorityChildName() || !childNode.getChild(childChangePath.getParent()).isEmpty()) {
                newChild = childNode.updateChild(childChangePath, changedSnap);
            } else {
                newChild = childNode;
            }
        }
        if (oldChild.equals(newChild)) {
            return oldViewCache;
        }
        IndexedNode newEventSnap = this.filter.updateChild(oldEventSnap.getIndexedNode(), childKey, newChild, childChangePath, source, accumulator);
        ViewCache newViewCache3 = oldViewCache.updateEventSnap(newEventSnap, oldEventSnap.isFullyInitialized(), this.filter.filtersNodes());
        return newViewCache3;
    }

    private static boolean cacheHasChild(ViewCache viewCache, ChildKey childKey) {
        return viewCache.getEventCache().isCompleteForChild(childKey);
    }

    private ViewCache applyUserMerge(ViewCache viewCache, Path path, CompoundWrite changedChildren, WriteTreeRef writesCache, Node serverCache, ChildChangeAccumulator accumulator) {
        ViewCache currentViewCache = viewCache;
        Iterator<Map.Entry<Path, Node>> it = changedChildren.iterator();
        while (it.hasNext()) {
            Map.Entry<Path, Node> entry = it.next();
            Path writePath = path.child(entry.getKey());
            if (cacheHasChild(viewCache, writePath.getFront())) {
                currentViewCache = applyUserOverwrite(currentViewCache, writePath, entry.getValue(), writesCache, serverCache, accumulator);
            }
        }
        Iterator<Map.Entry<Path, Node>> it2 = changedChildren.iterator();
        while (it2.hasNext()) {
            Map.Entry<Path, Node> entry2 = it2.next();
            Path writePath2 = path.child(entry2.getKey());
            if (!cacheHasChild(viewCache, writePath2.getFront())) {
                currentViewCache = applyUserOverwrite(currentViewCache, writePath2, entry2.getValue(), writesCache, serverCache, accumulator);
            }
        }
        return currentViewCache;
    }

    private ViewCache applyServerMerge(ViewCache viewCache, Path path, CompoundWrite changedChildren, WriteTreeRef writesCache, Node serverCache, boolean filterServerNode, ChildChangeAccumulator accumulator) {
        CompoundWrite actualMerge;
        if (viewCache.getServerCache().getNode().isEmpty() && !viewCache.getServerCache().isFullyInitialized()) {
            return viewCache;
        }
        ViewCache curViewCache = viewCache;
        if (path.isEmpty()) {
            actualMerge = changedChildren;
        } else {
            CompoundWrite actualMerge2 = CompoundWrite.emptyWrite();
            actualMerge = actualMerge2.addWrites(path, changedChildren);
        }
        Node serverNode = viewCache.getServerCache().getNode();
        Map<ChildKey, CompoundWrite> childCompoundWrites = actualMerge.childCompoundWrites();
        for (Map.Entry<ChildKey, CompoundWrite> childMerge : childCompoundWrites.entrySet()) {
            ChildKey childKey = childMerge.getKey();
            if (serverNode.hasChild(childKey)) {
                Node serverChild = serverNode.getImmediateChild(childKey);
                Node newChild = childMerge.getValue().apply(serverChild);
                curViewCache = applyServerOverwrite(curViewCache, new Path(childKey), newChild, writesCache, serverCache, filterServerNode, accumulator);
            }
        }
        for (Map.Entry<ChildKey, CompoundWrite> childMerge2 : childCompoundWrites.entrySet()) {
            ChildKey childKey2 = childMerge2.getKey();
            CompoundWrite childCompoundWrite = childMerge2.getValue();
            boolean isUnknownDeepMerge = !viewCache.getServerCache().isCompleteForChild(childKey2) && childCompoundWrite.rootWrite() == null;
            if (!serverNode.hasChild(childKey2) && !isUnknownDeepMerge) {
                Node serverChild2 = serverNode.getImmediateChild(childKey2);
                Node newChild2 = childMerge2.getValue().apply(serverChild2);
                curViewCache = applyServerOverwrite(curViewCache, new Path(childKey2), newChild2, writesCache, serverCache, filterServerNode, accumulator);
            }
        }
        return curViewCache;
    }

    private ViewCache ackUserWrite(ViewCache viewCache, Path ackPath, ImmutableTree<Boolean> affectedTree, WriteTreeRef writesCache, Node optCompleteCache, ChildChangeAccumulator accumulator) {
        if (writesCache.shadowingWrite(ackPath) != null) {
            return viewCache;
        }
        boolean filterServerNode = viewCache.getServerCache().isFiltered();
        CacheNode serverCache = viewCache.getServerCache();
        if (affectedTree.getValue() == null) {
            CompoundWrite changedChildren = CompoundWrite.emptyWrite();
            Iterator<Map.Entry<Path, Boolean>> it = affectedTree.iterator();
            CompoundWrite changedChildren2 = changedChildren;
            while (it.hasNext()) {
                Map.Entry<Path, Boolean> entry = it.next();
                Path mergePath = entry.getKey();
                Path serverCachePath = ackPath.child(mergePath);
                if (serverCache.isCompleteForPath(serverCachePath)) {
                    changedChildren2 = changedChildren2.addWrite(mergePath, serverCache.getNode().getChild(serverCachePath));
                }
            }
            return applyServerMerge(viewCache, ackPath, changedChildren2, writesCache, optCompleteCache, filterServerNode, accumulator);
        } else if ((ackPath.isEmpty() && serverCache.isFullyInitialized()) || serverCache.isCompleteForPath(ackPath)) {
            return applyServerOverwrite(viewCache, ackPath, serverCache.getNode().getChild(ackPath), writesCache, optCompleteCache, filterServerNode, accumulator);
        } else {
            if (!ackPath.isEmpty()) {
                return viewCache;
            }
            CompoundWrite changedChildren3 = CompoundWrite.emptyWrite();
            CompoundWrite changedChildren4 = changedChildren3;
            for (NamedNode child : serverCache.getNode()) {
                changedChildren4 = changedChildren4.addWrite(child.getName(), child.getNode());
            }
            return applyServerMerge(viewCache, ackPath, changedChildren4, writesCache, optCompleteCache, filterServerNode, accumulator);
        }
    }

    public ViewCache revertUserWrite(ViewCache viewCache, Path path, WriteTreeRef writesCache, Node optCompleteServerCache, ChildChangeAccumulator accumulator) {
        IndexedNode newEventCache;
        IndexedNode oldEventCache;
        ChildChangeAccumulator childChangeAccumulator;
        Node newNode;
        Node newChild;
        ChildChangeAccumulator childChangeAccumulator2;
        if (writesCache.shadowingWrite(path) != null) {
            return viewCache;
        }
        NodeFilter.CompleteChildSource source = new WriteTreeCompleteChildSource(writesCache, viewCache, optCompleteServerCache);
        IndexedNode oldEventCache2 = viewCache.getEventCache().getIndexedNode();
        if (path.isEmpty()) {
            oldEventCache = oldEventCache2;
            childChangeAccumulator = accumulator;
        } else if (path.getFront().isPriorityChildName()) {
            oldEventCache = oldEventCache2;
            childChangeAccumulator = accumulator;
        } else {
            ChildKey childKey = path.getFront();
            Node newChild2 = writesCache.calcCompleteChild(childKey, viewCache.getServerCache());
            if (newChild2 != null || !viewCache.getServerCache().isCompleteForChild(childKey)) {
                newChild = newChild2;
            } else {
                newChild = oldEventCache2.getNode().getImmediateChild(childKey);
            }
            if (newChild != null) {
                newEventCache = this.filter.updateChild(oldEventCache2, childKey, newChild, path.popFront(), source, accumulator);
                childChangeAccumulator2 = accumulator;
            } else if (newChild != null || !viewCache.getEventCache().getNode().hasChild(childKey)) {
                childChangeAccumulator2 = accumulator;
                newEventCache = oldEventCache2;
            } else {
                childChangeAccumulator2 = accumulator;
                newEventCache = this.filter.updateChild(oldEventCache2, childKey, EmptyNode.Empty(), path.popFront(), source, accumulator);
            }
            if (newEventCache.getNode().isEmpty() && viewCache.getServerCache().isFullyInitialized()) {
                Node complete = writesCache.calcCompleteEventCache(viewCache.getCompleteServerSnap());
                if (complete.isLeafNode()) {
                    IndexedNode indexedNode = IndexedNode.from(complete, this.filter.getIndex());
                    newEventCache = this.filter.updateFullNode(newEventCache, indexedNode, childChangeAccumulator2);
                }
            }
            return viewCache.updateEventSnap(newEventCache, !viewCache.getServerCache().isFullyInitialized() || writesCache.shadowingWrite(Path.getEmptyPath()) != null, this.filter.filtersNodes());
        }
        if (viewCache.getServerCache().isFullyInitialized()) {
            newNode = writesCache.calcCompleteEventCache(viewCache.getCompleteServerSnap());
        } else {
            newNode = writesCache.calcCompleteEventChildren(viewCache.getServerCache().getNode());
        }
        IndexedNode indexedNode2 = IndexedNode.from(newNode, this.filter.getIndex());
        newEventCache = this.filter.updateFullNode(oldEventCache, indexedNode2, childChangeAccumulator);
        return viewCache.updateEventSnap(newEventCache, !viewCache.getServerCache().isFullyInitialized() || writesCache.shadowingWrite(Path.getEmptyPath()) != null, this.filter.filtersNodes());
    }

    private ViewCache listenComplete(ViewCache viewCache, Path path, WriteTreeRef writesCache, Node serverCache, ChildChangeAccumulator accumulator) {
        CacheNode oldServerNode = viewCache.getServerCache();
        ViewCache newViewCache = viewCache.updateServerSnap(oldServerNode.getIndexedNode(), oldServerNode.isFullyInitialized() || path.isEmpty(), oldServerNode.isFiltered());
        return generateEventCacheAfterServerEvent(newViewCache, path, writesCache, NO_COMPLETE_SOURCE, accumulator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    public static class WriteTreeCompleteChildSource implements NodeFilter.CompleteChildSource {
        private final Node optCompleteServerCache;
        private final ViewCache viewCache;
        private final WriteTreeRef writes;

        public WriteTreeCompleteChildSource(WriteTreeRef writes, ViewCache viewCache, Node optCompleteServerCache) {
            this.writes = writes;
            this.viewCache = viewCache;
            this.optCompleteServerCache = optCompleteServerCache;
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public Node getCompleteChild(ChildKey childKey) {
            CacheNode serverNode;
            CacheNode node = this.viewCache.getEventCache();
            if (node.isCompleteForChild(childKey)) {
                return node.getNode().getImmediateChild(childKey);
            }
            if (this.optCompleteServerCache != null) {
                serverNode = new CacheNode(IndexedNode.from(this.optCompleteServerCache, KeyIndex.getInstance()), true, false);
            } else {
                serverNode = this.viewCache.getServerCache();
            }
            return this.writes.calcCompleteChild(childKey, serverNode);
        }

        @Override // com.google.firebase.database.core.view.filter.NodeFilter.CompleteChildSource
        public NamedNode getChildAfterChild(Index index, NamedNode child, boolean reverse) {
            Node completeServerData = this.optCompleteServerCache;
            if (completeServerData == null) {
                completeServerData = this.viewCache.getCompleteServerSnap();
            }
            return this.writes.calcNextNodeAfterPost(completeServerData, child, reverse, index);
        }
    }
}
