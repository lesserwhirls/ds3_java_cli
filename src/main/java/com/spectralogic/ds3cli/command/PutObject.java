package com.spectralogic.ds3cli.command;

import com.spectralogic.ds3cli.Arguments;
import com.spectralogic.ds3cli.BadArgumentException;
import com.spectralogic.ds3client.Ds3Client;
import com.spectralogic.ds3client.commands.PutObjectRequest;
import com.spectralogic.ds3client.networking.FailedRequestException;
import org.apache.commons.cli.MissingOptionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PutObject extends CliCommand {

    private String bucketName;
    private File objectFile;
    private String objectName;

    public PutObject(Ds3Client client) {
        super(client);
    }

    @Override
    public CliCommand init(Arguments args) throws Exception {
        bucketName = args.getBucket();
        if (bucketName == null) {
            throw new MissingOptionException("The get object command requires '-b' to be set.");
        }
        objectName = args.getObjectName();
        if (objectName == null) {
            throw new MissingOptionException("The get object command requires '-o' to be set.");
        }

        objectFile = new File(objectName);
        if(!objectFile.exists()) {
            throw new BadArgumentException("File '"+ objectName +"' does not exist.");
        }
        if (!objectFile.isFile()) {
            throw new BadArgumentException("The '-o' command must be a file and not a directory.");
        }
        return this;
    }

    @Override
    public String call() throws Exception {

        try {
            getClient().putObject(new PutObjectRequest(bucketName, objectName, objectFile.length(), new FileInputStream(objectFile)));
        }
        catch(final FailedRequestException e) {
            return "ERROR: " + e.getMessage();
        }
        catch(final IOException e) {
            return "ERROR: Encountered an error when communicating with the ds3 appliance.  The error was: " + e.getMessage();
        }

        return "Success: Finished writing file to ds3 appliance.";
    }
}