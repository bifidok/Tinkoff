package edu.hw11.task3;

import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

enum IntegerSum implements StackManipulation {
    INSTANCE;

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Size apply(MethodVisitor methodVisitor, Implementation.Context implementationContext) {
        Label start = new Label();
        Label labelIfLessOrEqual0 = new Label();
        Label labelIfEqual1 = new Label();
        Label labelIfMoreThan1 = new Label();
        Label finish = new Label();

        methodVisitor.visitCode();

        int indexArg = 1;
        methodVisitor.visitLocalVariable("arg", "J", null, start, finish, indexArg);
        int indexPrevSum = 3;
        methodVisitor.visitLocalVariable("prevSum", "J", null, start, finish, indexPrevSum);
        int indexPrevPrevSum = 5;
        methodVisitor.visitLocalVariable("prevPrevSum", "J", null, start, finish, indexPrevPrevSum);
        int indexAnswerSum = 7;
        methodVisitor.visitLocalVariable("answer", "J", null, start, finish, indexAnswerSum);
        int indexCounter = 9;
        methodVisitor.visitLocalVariable("counter", "J", null, start, finish, indexCounter);

        methodVisitor.visitLabel(start);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LCMP);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, labelIfLessOrEqual0);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitLabel(labelIfLessOrEqual0);
        methodVisitor.visitFrame(
            Opcodes.F_SAME,
            0, null,
            0, null
        );
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LCMP);
        methodVisitor.visitJumpInsn(Opcodes.IFNE, labelIfEqual1);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        methodVisitor.visitLabel(labelIfEqual1);
        methodVisitor.visitFrame(
            Opcodes.F_SAME,
            0, null,
            0, null
        );
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevSum);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevPrevSum);
        methodVisitor.visitInsn(Opcodes.LCONST_0);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexAnswerSum);
        methodVisitor.visitFrame(
            Opcodes.F_APPEND,
            indexPrevSum, new Object[] {Opcodes.LONG, Opcodes.LONG, Opcodes.LONG},
            0,
            null
        );
        methodVisitor.visitInsn(Opcodes.ICONST_2);
        methodVisitor.visitInsn(Opcodes.I2L);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexCounter);

        methodVisitor.visitFrame(
            Opcodes.F_APPEND,
            indexArg, new Object[] {Opcodes.LONG},
            0,
            null
        );
        methodVisitor.visitLabel(labelIfMoreThan1);
        methodVisitor.visitFrame(
            Opcodes.F_SAME,
            0, null,
            0, null
        );
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexCounter);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexArg);
        methodVisitor.visitInsn(Opcodes.LCMP);
        methodVisitor.visitJumpInsn(Opcodes.IFGT, finish);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevSum);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevPrevSum);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexAnswerSum);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexPrevPrevSum);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevSum);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexAnswerSum);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexPrevPrevSum);
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexCounter);
        methodVisitor.visitInsn(Opcodes.LCONST_1);
        methodVisitor.visitInsn(Opcodes.LADD);
        methodVisitor.visitVarInsn(Opcodes.LSTORE, indexCounter);
        methodVisitor.visitJumpInsn(Opcodes.GOTO, labelIfMoreThan1);

        methodVisitor.visitLabel(finish);
        methodVisitor.visitFrame(
            Opcodes.F_SAME,
            0, null,
            0, null
        );
        methodVisitor.visitVarInsn(Opcodes.LLOAD, indexAnswerSum);
        methodVisitor.visitInsn(Opcodes.LRETURN);

        return new Size(2, 4);
    }
}
