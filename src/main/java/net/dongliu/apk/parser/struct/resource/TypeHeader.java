package net.dongliu.apk.parser.struct.resource;

import net.dongliu.apk.parser.struct.ChunkHeader;
import net.dongliu.apk.parser.struct.ChunkType;
import net.dongliu.apk.parser.utils.Unsigned;

/**
 * @author dongliu
 */
/*
 * See ResTable_type in file frameworks/base/libs/androidfw/include/androidfw/ResourceTypes.h
 */
public class TypeHeader extends ChunkHeader {

    public static final long NO_ENTRY = 0xFFFFFFFFL;

    // The type identifier this chunk is holding.  Type IDs start at 1 (corresponding to the value
    // of the type bits in a resource identifier).  0 is invalid.
    // uint8_t
    private byte id;

    // Must be 0. uint8_t
    private byte flags;
    // reserved
    private short res;

    // Number of uint32_t entry indices that follow. uint32
    private int entryCount;

    // Offset from header where ResTable_entry data starts.uint32_t
    private int entriesStart;

    // Configuration this collection of entries is designed for.
    private ResTableConfig config;

    public TypeHeader(int headerSize, long chunkSize) {
        super(ChunkType.TABLE_TYPE, headerSize, chunkSize);
    }

    public short getId() {
        return Unsigned.toShort(id);
    }

    public void setId(short id) {
        this.id = Unsigned.toUByte(id);
    }

    public short getFlags() {
        return Unsigned.toUShort(flags);
    }

    public void setFlags(short flags) {
        this.flags = Unsigned.toUByte(flags);
    }

    public int getRes() {
        return Unsigned.toInt(res);
    }

    public void setRes(int res1) {
        this.res = Unsigned.toUShort(res);
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(long entryCount) {
        this.entryCount = Unsigned.ensureUInt(entryCount);
    }

    public int getEntriesStart() {
        return entriesStart;
    }

    public void setEntriesStart(long entriesStart) {
        this.entriesStart = Unsigned.ensureUInt(entriesStart);
    }

    public ResTableConfig getConfig() {
        return config;
    }

    public void setConfig(ResTableConfig config) {
        this.config = config;
    }
}
