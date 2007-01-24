/*
 *
 * Enginuity Open-Source Tuning, Logging and Reflashing
 * Copyright (C) 2006 Enginuity.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

package enginuity.util;

import enginuity.newmaps.ecumetadata.Scale;
import enginuity.newmaps.ecumetadata.TableMetadata;

@SuppressWarnings({"UnnecessaryBoxing"})
public final class ByteUtil {

    private ByteUtil() {
    }

    public static int asUnsignedInt(byte[] bytes) {
        int i = 0;
        for (int j = 0; j < bytes.length; j++) {
            if (j > 0) {
                i <<= 8;
            }
            i |= bytes[j] & 0xFF;
        }
        return i;
    }
    
    public static int asSignedInt(byte[] bytes) {
        int i = 0;
        for (int j = 0; j < bytes.length; j++) {
            if (j > 0) {
                i <<= 8;
            }
            i |= bytes[j];
        }
        return i;
    }    
    
    private int getCellLengthInBytes(TableMetadata metadata) {        
        int type = metadata.getScale().getStorageType();
        
        if (type == Scale.STORAGE_TYPE_CHAR) return 1;
        else if (type == Scale.STORAGE_TYPE_FLOAT) return 4;
        else if (type == Scale.STORAGE_TYPE_HEX) return 1;
        else if (type == Scale.STORAGE_TYPE_INT16) return 2;
        else if (type == Scale.STORAGE_TYPE_INT8) return 1;
        else if (type == Scale.STORAGE_TYPE_UINT16) return 2;
        else if (type == Scale.STORAGE_TYPE_UINT8) return 1;
        else if (type == Scale.STORAGE_TYPE_UINT32) return 4;
        else if (type == Scale.STORAGE_TYPE_INT32) return 4;       
        
        else return 0; 
    }    

    public static byte asByte(int i) {
        return Integer.valueOf(i).byteValue();
    }

    public static int asInt(byte b) {
        return Byte.valueOf(b).intValue();
    }

    public static byte[] asUnsignedBytes(int i) {
        byte[] b = new byte[4];
        for (int j = 0; j < 4; j++) {
            int offset = (b.length - 1 - j) << 3;
            b[j] = (byte) ((i >>> offset) & 0xFF);
        }
        return b;
    }
    
    public static byte[] reverseEndian(byte[] data) {
        byte[] newData = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            newData[data.length - 1 - i] = data[i];
        }
        return newData;
    }
    
    public static byte[] asSignedBytes(int i) {
        byte[] b = new byte[4];
        for (int j = 0; j < 4; j++) {
            int offset = (b.length - 1 - j) << 3;
            b[j] = (byte) (i >>> offset);
        }
        return b;
    }    

    public static float asFloat(byte[] bytes) {
        return Float.intBitsToFloat(asUnsignedInt(bytes));
    }

}
