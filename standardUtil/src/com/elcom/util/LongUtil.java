package com.elcom.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class LongUtil {
	
	public static int priceToSalePercent(BigDecimal price, BigDecimal priceDiscount) {
		
		if( priceDiscount == null || price.compareTo(priceDiscount) == -1 )
			return 0;
		
		// (giá bán - giá bán sau giảm giá) / giá bán * 100
    	return (
	    			(price.subtract(priceDiscount))
	    			.divide(price, 8, RoundingMode.HALF_EVEN)
	    			.multiply(new BigDecimal(100))
    			).intValue();
	}
	
	public static String formatCurrentcy(BigDecimal input) {
		
		if( input == null )
			return "0";
		
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
    	DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

    	symbols.setGroupingSeparator(',');
    	formatter.setDecimalFormatSymbols(symbols);
    	
    	return formatter.format(input.longValue());
	}
	
	public static Long toId(Long value){
		return value == null || value == 0 ? new Long("1") : value;		
	}
	
	public static Long uniqueId()
    {
        long val = -1;
        do
        {
            final UUID uid = UUID.randomUUID();
            final ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
            buffer.putLong(uid.getLeastSignificantBits());
            buffer.putLong(uid.getMostSignificantBits());
            final BigInteger bi = new BigInteger(buffer.array());
            val = bi.longValue();
        } 
         // We also make sure that the ID is in positive space, if its not we simply repeat the process
        while (val < 0);
        return val;
    }
	
}
