package com.example.virtualizationdetection;

/**
 * @author Lody
 * @version 1.0
 */

public class StructMember {

    /**
     * Address of struct
     */
    private long structAddress;
    /**
     * Offset address of this element in struct
     */
    private long varOffsetAddress;

    /**
     * Length of this element in struct
     */
    private int length;

    public StructMember(long structAddress, long varOffsetAddress, int length) {
        this.structAddress = structAddress;
        this.varOffsetAddress = varOffsetAddress;
        this.length = length;
    }


    public long getStructAddress() {
        return structAddress;
    }

    public long getVarAddress() {
        return structAddress + varOffsetAddress;
    }

    public long getVarOffsetAddress() {
        return varOffsetAddress;
    }

    public long getLength() {
        return length;
    }


    public byte[] read() {
        return LegendNative.memget(structAddress + varOffsetAddress,length);
    }


    public int readInt() {
        return Platform.getPlatform().orderByteToInt(read());
    }

    public long readLong() {
        return Platform.getPlatform().orderByteToLong(read());
    }

    public short readShort() {
        return Platform.getPlatform().orderByteToShort(read());
    }

}
