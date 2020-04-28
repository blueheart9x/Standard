package com.elcom.data.entity.dto;

import com.elcom.data.entity.User;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class UserDataPaging implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 122103466283664407L;

    private List<User> dataRows;
    private BigInteger totalRows;

    public UserDataPaging() {
        totalRows = new BigInteger("0");
        dataRows = new ArrayList<User>();
    }

    public List<User> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<User> dataRows) {
        this.dataRows = dataRows;
    }

    public BigInteger getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(BigInteger totalRows) {
        this.totalRows = totalRows;
    }
}
