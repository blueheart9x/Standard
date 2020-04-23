package com.elcom.util;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class CRCUtils {

	public static String binaryStringToHexCrc(String binaryString) {

        CRC32 crc = new CRC32();
        crc.update(binaryString.getBytes());
        Long crcValue = crc.getValue();
        
        //System.out.println(crcValue);
        //System.out.println( HexUtils.decToHex(crcValue.intValue(), 8) );
        
        return HexUtils.decToHex(crcValue.intValue(), 8);
	}
	
	public static String binaryStringToHexCrc(byte[] bytesArrDataContent) {
		
		String result = "";
		
		try {
			
			CRC32 crc = new CRC32();
			
	        crc.update(bytesArrDataContent);
	        
	        Long crcValue = crc.getValue();
	        
	        //System.out.println(crcValue);
	        //System.out.println( HexUtils.decToHex(crcValue.intValue(), 8) );
	        
	        result = HexUtils.decToHex(crcValue.intValue(), 8);
		}catch(Exception ex) {
			System.out.println("CRCUtils.binaryStringToHexCrc.ex: " + ex.toString());
		}
		
        return result;
	}
	
	public static String binaryStringToHexCrc2(byte[] bytesArrDataContent) {

        Checksum checksum = new CRC32();
         
        // update the current checksum with the specified array of bytes
        checksum.update(bytesArrDataContent, 0, bytesArrDataContent.length);
          
        // get the current checksum value
        Long checksumValue = checksum.getValue();
        
        //System.out.println(checksumValue);
        //System.out.println( HexUtils.decToHex(checksumValue.intValue(), 8) );
        
        return HexUtils.decToHex(checksumValue.intValue(), 8);
	}
	
	public static String binaryStringToHexCrc2(String binaryString) {

        // get bytes from string
        byte bytes[] = binaryString.getBytes();
          
        Checksum checksum = new CRC32();
         
        // update the current checksum with the specified array of bytes
        checksum.update(bytes, 0, bytes.length);
          
        // get the current checksum value
        Long checksumValue = checksum.getValue();
        
        //System.out.println(checksumValue);
        //System.out.println( HexUtils.decToHex(checksumValue.intValue(), 8) );
        
        return HexUtils.decToHex(checksumValue.intValue(), 8);
	}
	
	public static void main(String[] args) {
		
		char intValue = (char) 0x80;
		
		
		System.out.println(intValue);
	
		
		/*System.out.println(binaryStringToHexCrc(hexString));
		
		System.out.println("------------------------------");
		
		System.out.println(binaryStringToHexCrc2(hexString));*/
	}
}
