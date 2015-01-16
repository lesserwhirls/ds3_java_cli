package com.spectralogic.ds3cli.models;

import com.spectralogic.ds3client.models.Contents;

import java.util.Iterator;

public class BucketResult {
    final private String bucketName;
    final private Iterator<Contents> objIterator;

    public String getBucketName() {
        return bucketName;
    }

    public Iterator<Contents> getObjIterator() {
        return objIterator;
    }

    public BucketResult( final String bucketName, final Iterator<Contents> objIterator) {
        this.bucketName = bucketName;
        this.objIterator = objIterator;
    }
}
