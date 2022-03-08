package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GROUPS = 32;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 7;
    static boolean[] flags = new boolean[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkMatchParent(ConstraintWidgetContainer container, LinearSystem system, ConstraintWidget widget) {
        if (container.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int left = widget.mLeft.mMargin;
            int right = container.getWidth() - widget.mRight.mMargin;
            widget.mLeft.mSolverVariable = system.createObjectVariable(widget.mLeft);
            widget.mRight.mSolverVariable = system.createObjectVariable(widget.mRight);
            system.addEquality(widget.mLeft.mSolverVariable, left);
            system.addEquality(widget.mRight.mSolverVariable, right);
            widget.mHorizontalResolution = 2;
            widget.setHorizontalDimension(left, right);
        }
        if (container.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int top = widget.mTop.mMargin;
            int bottom = container.getHeight() - widget.mBottom.mMargin;
            widget.mTop.mSolverVariable = system.createObjectVariable(widget.mTop);
            widget.mBottom.mSolverVariable = system.createObjectVariable(widget.mBottom);
            system.addEquality(widget.mTop.mSolverVariable, top);
            system.addEquality(widget.mBottom.mSolverVariable, bottom);
            if (widget.mBaselineDistance > 0 || widget.getVisibility() == 8) {
                widget.mBaseline.mSolverVariable = system.createObjectVariable(widget.mBaseline);
                system.addEquality(widget.mBaseline.mSolverVariable, widget.mBaselineDistance + top);
            }
            widget.mVerticalResolution = 2;
            widget.setVerticalDimension(top, bottom);
        }
    }

    private static boolean optimizableMatchConstraint(ConstraintWidget constraintWidget, int orientation) {
        if (constraintWidget.mListDimensionBehaviors[orientation] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            return false;
        }
        char c = 1;
        if (constraintWidget.mDimensionRatio != 0.0f) {
            ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
            if (orientation != 0) {
                c = 0;
            }
            return dimensionBehaviourArr[c] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT ? false : false;
        }
        if (orientation == 0) {
            if (constraintWidget.mMatchConstraintDefaultWidth != 0 || constraintWidget.mMatchConstraintMinWidth != 0 || constraintWidget.mMatchConstraintMaxWidth != 0) {
                return false;
            }
        } else if (constraintWidget.mMatchConstraintDefaultHeight != 0 || constraintWidget.mMatchConstraintMinHeight != 0 || constraintWidget.mMatchConstraintMaxHeight != 0) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void analyze(int optimisationLevel, ConstraintWidget widget) {
        widget.updateResolutionNodes();
        ResolutionAnchor leftNode = widget.mLeft.getResolutionNode();
        ResolutionAnchor topNode = widget.mTop.getResolutionNode();
        ResolutionAnchor rightNode = widget.mRight.getResolutionNode();
        ResolutionAnchor bottomNode = widget.mBottom.getResolutionNode();
        boolean optimiseDimensions = (optimisationLevel & 8) == 8;
        boolean isOptimizableHorizontalMatch = widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 0);
        if (!(leftNode.type == 4 || rightNode.type == 4)) {
            if (widget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED || (isOptimizableHorizontalMatch && widget.getVisibility() == 8)) {
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget != null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget == null && widget.mRight.mTarget != null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    leftNode.dependsOn(rightNode, -widget.getWidth());
                    if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -widget.getWidth());
                    }
                } else if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                    leftNode.setType(2);
                    rightNode.setType(2);
                    if (optimiseDimensions) {
                        widget.getResolutionWidth().addDependent(leftNode);
                        widget.getResolutionWidth().addDependent(rightNode);
                        leftNode.setOpposite(rightNode, -1, widget.getResolutionWidth());
                        rightNode.setOpposite(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        leftNode.setOpposite(rightNode, -widget.getWidth());
                        rightNode.setOpposite(leftNode, widget.getWidth());
                    }
                }
            } else if (isOptimizableHorizontalMatch) {
                int width = widget.getWidth();
                leftNode.setType(1);
                rightNode.setType(1);
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, width);
                    }
                } else if (widget.mLeft.mTarget == null || widget.mRight.mTarget != null) {
                    if (widget.mLeft.mTarget != null || widget.mRight.mTarget == null) {
                        if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                            if (optimiseDimensions) {
                                widget.getResolutionWidth().addDependent(leftNode);
                                widget.getResolutionWidth().addDependent(rightNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                leftNode.setType(3);
                                rightNode.setType(3);
                                leftNode.setOpposite(rightNode, 0.0f);
                                rightNode.setOpposite(leftNode, 0.0f);
                            } else {
                                leftNode.setType(2);
                                rightNode.setType(2);
                                leftNode.setOpposite(rightNode, -width);
                                rightNode.setOpposite(leftNode, width);
                                widget.setWidth(width);
                            }
                        }
                    } else if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -width);
                    }
                } else if (optimiseDimensions) {
                    rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                } else {
                    rightNode.dependsOn(leftNode, width);
                }
            }
        }
        boolean isOptimizableVerticalMatch = widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 1);
        if (topNode.type != 4 && bottomNode.type != 4) {
            if (widget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED || (isOptimizableVerticalMatch && widget.getVisibility() == 8)) {
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaseline.mTarget != null) {
                        widget.mBaseline.getResolutionNode().setType(1);
                        topNode.dependsOn(1, widget.mBaseline.getResolutionNode(), -widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget == null && widget.mBottom.mTarget != null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                    topNode.setType(2);
                    bottomNode.setType(2);
                    if (optimiseDimensions) {
                        topNode.setOpposite(bottomNode, -1, widget.getResolutionHeight());
                        bottomNode.setOpposite(topNode, 1, widget.getResolutionHeight());
                        widget.getResolutionHeight().addDependent(topNode);
                        widget.getResolutionWidth().addDependent(bottomNode);
                    } else {
                        topNode.setOpposite(bottomNode, -widget.getHeight());
                        bottomNode.setOpposite(topNode, widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                }
            } else if (isOptimizableVerticalMatch) {
                int height = widget.getHeight();
                topNode.setType(1);
                bottomNode.setType(1);
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, height);
                    }
                } else if (widget.mTop.mTarget == null || widget.mBottom.mTarget != null) {
                    if (widget.mTop.mTarget != null || widget.mBottom.mTarget == null) {
                        if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                            if (optimiseDimensions) {
                                widget.getResolutionHeight().addDependent(topNode);
                                widget.getResolutionWidth().addDependent(bottomNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                topNode.setType(3);
                                bottomNode.setType(3);
                                topNode.setOpposite(bottomNode, 0.0f);
                                bottomNode.setOpposite(topNode, 0.0f);
                                return;
                            }
                            topNode.setType(2);
                            bottomNode.setType(2);
                            topNode.setOpposite(bottomNode, -height);
                            bottomNode.setOpposite(topNode, height);
                            widget.setHeight(height);
                            if (widget.mBaselineDistance > 0) {
                                widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                            }
                        }
                    } else if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -height);
                    }
                } else if (optimiseDimensions) {
                    bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                } else {
                    bottomNode.dependsOn(topNode, height);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean applyChainOptimized(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ChainHead chainHead) {
        boolean isChainSpread;
        boolean isChainSpread2;
        boolean isChainSpreadInside;
        float distance;
        boolean isChainPacked;
        boolean isChainSpread3;
        float distance2;
        ConstraintWidget widget;
        ConstraintWidget first;
        boolean isChainSpreadInside2;
        int numVisibleWidgets;
        float extraMargin;
        float dimension;
        float distance3;
        boolean isChainPacked2;
        ResolutionAnchor lastNode;
        float totalWeights;
        float dimension2;
        boolean isChainSpread4;
        boolean isChainPacked3;
        int numMatchConstraints;
        int numMatchConstraints2;
        ConstraintWidget next;
        ConstraintWidget first2 = chainHead.mFirst;
        ConstraintWidget last = chainHead.mLast;
        ConstraintWidget firstVisibleWidget = chainHead.mFirstVisibleWidget;
        ConstraintWidget lastVisibleWidget = chainHead.mLastVisibleWidget;
        ConstraintWidget head = chainHead.mHead;
        float totalWeights2 = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget previousMatchConstraintsWidget = chainHead.mLastMatchConstraintWidget;
        boolean isWrapContent = container.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (orientation == 0) {
            boolean isChainSpread5 = head.mHorizontalChainStyle == 0;
            boolean isChainSpreadInside3 = head.mHorizontalChainStyle == 1;
            isChainSpread2 = head.mHorizontalChainStyle == 2;
            isChainSpread = isChainSpread5;
            isChainSpreadInside = isChainSpreadInside3;
        } else {
            boolean isChainSpread6 = head.mVerticalChainStyle == 0;
            boolean isChainSpreadInside4 = head.mVerticalChainStyle == 1;
            isChainSpread2 = head.mVerticalChainStyle == 2;
            isChainSpread = isChainSpread6;
            isChainSpreadInside = isChainSpreadInside4;
        }
        float totalSize = 0.0f;
        float totalMargins = 0.0f;
        int numMatchConstraints3 = 0;
        ConstraintAnchor nextAnchor = null;
        ConstraintWidget widget2 = first2;
        int numVisibleWidgets2 = 0;
        while (nextAnchor == null) {
            if (widget2.getVisibility() != 8) {
                numVisibleWidgets2++;
                if (orientation == 0) {
                    totalSize += widget2.getWidth();
                } else {
                    totalSize += widget2.getHeight();
                }
                if (widget2 != firstVisibleWidget) {
                    totalSize += widget2.mListAnchors[offset].getMargin();
                }
                if (widget2 != lastVisibleWidget) {
                    totalSize += widget2.mListAnchors[offset + 1].getMargin();
                }
                totalMargins = totalMargins + widget2.mListAnchors[offset].getMargin() + widget2.mListAnchors[offset + 1].getMargin();
            }
            ConstraintAnchor constraintAnchor = widget2.mListAnchors[offset];
            if (widget2.getVisibility() != 8 && widget2.mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                numMatchConstraints3++;
                if (orientation == 0) {
                    if (!(widget2.mMatchConstraintDefaultWidth == 0 && widget2.mMatchConstraintMinWidth == 0 && widget2.mMatchConstraintMaxWidth == 0)) {
                        return false;
                    }
                } else if (!(widget2.mMatchConstraintDefaultHeight == 0 && widget2.mMatchConstraintMinHeight == 0 && widget2.mMatchConstraintMaxHeight == 0)) {
                    return false;
                }
                if (widget2.mDimensionRatio != 0.0f) {
                    return false;
                }
            }
            ConstraintAnchor nextAnchor2 = widget2.mListAnchors[offset + 1].mTarget;
            if (nextAnchor2 != null) {
                ConstraintWidget next2 = nextAnchor2.mOwner;
                numMatchConstraints2 = numMatchConstraints3;
                next = (next2.mListAnchors[offset].mTarget == null || next2.mListAnchors[offset].mTarget.mOwner != widget2) ? null : next2;
            } else {
                numMatchConstraints2 = numMatchConstraints3;
                next = null;
            }
            if (next != null) {
                widget2 = next;
                nextAnchor = nextAnchor;
            } else {
                nextAnchor = 1;
            }
            numMatchConstraints3 = numMatchConstraints2;
            previousMatchConstraintsWidget = previousMatchConstraintsWidget;
        }
        ResolutionAnchor firstNode = first2.mListAnchors[offset].getResolutionNode();
        ResolutionAnchor lastNode2 = last.mListAnchors[offset + 1].getResolutionNode();
        if (firstNode.target != null && lastNode2.target != null && firstNode.target.state == 1 && lastNode2.target.state == 1) {
            if (numMatchConstraints3 > 0 && numMatchConstraints3 != numVisibleWidgets2) {
                return false;
            }
            float extraMargin2 = 0.0f;
            if (isChainSpread2 || isChainSpread || isChainSpreadInside) {
                if (firstVisibleWidget != null) {
                    extraMargin2 = firstVisibleWidget.mListAnchors[offset].getMargin();
                }
                if (lastVisibleWidget != null) {
                    extraMargin2 += lastVisibleWidget.mListAnchors[offset + 1].getMargin();
                }
            }
            float firstOffset = firstNode.target.resolvedOffset;
            float lastOffset = lastNode2.target.resolvedOffset;
            if (firstOffset < lastOffset) {
                distance = (lastOffset - firstOffset) - totalSize;
            } else {
                distance = (firstOffset - lastOffset) - totalSize;
            }
            if (numMatchConstraints3 <= 0 || numMatchConstraints3 != numVisibleWidgets2) {
                boolean isChainSpread7 = isChainSpread;
                if (distance < 0.0f) {
                    isChainPacked = true;
                    isChainSpread7 = false;
                    isChainSpread3 = false;
                } else {
                    isChainSpread3 = isChainSpreadInside;
                    isChainPacked = isChainSpread2;
                }
                if (isChainPacked) {
                    float distance4 = (first2.getBiasPercent(orientation) * (distance - extraMargin2)) + firstOffset;
                    ConstraintWidget widget3 = first2;
                    while (widget3 != null) {
                        if (LinearSystem.sMetrics != null) {
                            isChainPacked2 = isChainPacked;
                            LinearSystem.sMetrics.nonresolvedWidgets--;
                            LinearSystem.sMetrics.resolvedWidgets++;
                            LinearSystem.sMetrics.chainConnectionResolved++;
                        } else {
                            isChainPacked2 = isChainPacked;
                        }
                        ConstraintWidget next3 = widget3.mNextChainWidget[orientation];
                        if (next3 != null || widget3 == last) {
                            if (orientation == 0) {
                                dimension2 = widget3.getWidth();
                            } else {
                                dimension2 = widget3.getHeight();
                            }
                            float distance5 = distance4 + widget3.mListAnchors[offset].getMargin();
                            totalWeights = totalWeights2;
                            widget3.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, distance5);
                            lastNode = lastNode2;
                            widget3.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, distance5 + dimension2);
                            widget3.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                            widget3.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                            distance4 = distance5 + dimension2 + widget3.mListAnchors[offset + 1].getMargin();
                        } else {
                            totalWeights = totalWeights2;
                            lastNode = lastNode2;
                        }
                        widget3 = next3;
                        isChainPacked = isChainPacked2;
                        totalWeights2 = totalWeights;
                        lastNode2 = lastNode;
                    }
                    return true;
                } else if (!isChainSpread7 && !isChainSpread3) {
                    return true;
                } else {
                    if (isChainSpread7) {
                        distance -= extraMargin2;
                    } else if (isChainSpread3) {
                        distance -= extraMargin2;
                    }
                    float gap = distance / (numVisibleWidgets2 + 1);
                    if (isChainSpread3) {
                        if (numVisibleWidgets2 > 1) {
                            gap = distance / (numVisibleWidgets2 - 1);
                        } else {
                            gap = distance / 2.0f;
                        }
                    }
                    float distance6 = firstOffset;
                    if (first2.getVisibility() != 8) {
                        distance6 += gap;
                    }
                    if (isChainSpread3 && numVisibleWidgets2 > 1) {
                        distance6 = firstOffset + firstVisibleWidget.mListAnchors[offset].getMargin();
                    }
                    if (!isChainSpread7 || firstVisibleWidget == null) {
                        widget = first2;
                        distance2 = distance6;
                    } else {
                        widget = first2;
                        distance2 = distance6 + firstVisibleWidget.mListAnchors[offset].getMargin();
                    }
                    while (widget != null) {
                        if (LinearSystem.sMetrics != null) {
                            first = first2;
                            LinearSystem.sMetrics.nonresolvedWidgets--;
                            isChainSpreadInside2 = isChainSpread3;
                            numVisibleWidgets = numVisibleWidgets2;
                            LinearSystem.sMetrics.resolvedWidgets++;
                            LinearSystem.sMetrics.chainConnectionResolved++;
                        } else {
                            isChainSpreadInside2 = isChainSpread3;
                            first = first2;
                            numVisibleWidgets = numVisibleWidgets2;
                        }
                        ConstraintWidget next4 = widget.mNextChainWidget[orientation];
                        if (next4 != null || widget == last) {
                            if (orientation == 0) {
                                dimension = widget.getWidth();
                            } else {
                                dimension = widget.getHeight();
                            }
                            if (widget != firstVisibleWidget) {
                                distance3 = distance2 + widget.mListAnchors[offset].getMargin();
                            } else {
                                distance3 = distance2;
                            }
                            widget.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, distance3);
                            extraMargin = extraMargin2;
                            widget.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, distance3 + dimension);
                            widget.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                            widget.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                            distance2 = distance3 + widget.mListAnchors[offset + 1].getMargin() + dimension;
                            if (!(next4 == null || next4.getVisibility() == 8)) {
                                distance2 += gap;
                            }
                        } else {
                            extraMargin = extraMargin2;
                        }
                        widget = next4;
                        numVisibleWidgets2 = numVisibleWidgets;
                        isChainSpread3 = isChainSpreadInside2;
                        first2 = first;
                        extraMargin2 = extraMargin;
                    }
                    return true;
                }
            } else {
                if (widget2.getParent() != null && widget2.getParent().mListDimensionBehaviors[orientation] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    return false;
                }
                float distance7 = (distance + totalSize) - totalMargins;
                ConstraintWidget widget4 = first2;
                float position = firstOffset;
                while (widget4 != null) {
                    if (LinearSystem.sMetrics != null) {
                        isChainPacked3 = isChainSpread2;
                        isChainSpread4 = isChainSpread;
                        LinearSystem.sMetrics.nonresolvedWidgets--;
                        LinearSystem.sMetrics.resolvedWidgets++;
                        LinearSystem.sMetrics.chainConnectionResolved++;
                    } else {
                        isChainPacked3 = isChainSpread2;
                        isChainSpread4 = isChainSpread;
                    }
                    ConstraintWidget next5 = widget4.mNextChainWidget[orientation];
                    if (next5 != null || widget4 == last) {
                        float dimension3 = distance7 / numMatchConstraints3;
                        if (totalWeights2 > 0.0f) {
                            if (widget4.mWeight[orientation] == -1.0f) {
                                dimension3 = 0.0f;
                            } else {
                                dimension3 = (widget4.mWeight[orientation] * distance7) / totalWeights2;
                            }
                        }
                        if (widget4.getVisibility() == 8) {
                            dimension3 = 0.0f;
                        }
                        float position2 = position + widget4.mListAnchors[offset].getMargin();
                        widget4.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, position2);
                        numMatchConstraints = numMatchConstraints3;
                        widget4.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, position2 + dimension3);
                        widget4.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                        widget4.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                        position = position2 + dimension3 + widget4.mListAnchors[offset + 1].getMargin();
                    } else {
                        numMatchConstraints = numMatchConstraints3;
                    }
                    widget4 = next5;
                    isChainSpread2 = isChainPacked3;
                    isChainSpread = isChainSpread4;
                    numMatchConstraints3 = numMatchConstraints;
                }
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setOptimizedWidget(ConstraintWidget widget, int orientation, int resolvedOffset) {
        int startOffset = orientation * 2;
        int endOffset = startOffset + 1;
        widget.mListAnchors[startOffset].getResolutionNode().resolvedTarget = widget.getParent().mLeft.getResolutionNode();
        widget.mListAnchors[startOffset].getResolutionNode().resolvedOffset = resolvedOffset;
        widget.mListAnchors[startOffset].getResolutionNode().state = 1;
        widget.mListAnchors[endOffset].getResolutionNode().resolvedTarget = widget.mListAnchors[startOffset].getResolutionNode();
        widget.mListAnchors[endOffset].getResolutionNode().resolvedOffset = widget.getLength(orientation);
        widget.mListAnchors[endOffset].getResolutionNode().state = 1;
    }
}
