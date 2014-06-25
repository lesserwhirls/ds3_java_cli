/*
 * ******************************************************************************
 *   Copyright 2014 Spectra Logic Corporation. All Rights Reserved.
 *   Licensed under the Apache License, Version 2.0 (the "License"). You may not use
 *   this file except in compliance with the License. A copy of the License is located at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file.
 *   This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 *   CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 * ****************************************************************************
 */

package com.spectralogic.ds3cli.command;

import com.spectralogic.ds3cli.Arguments;
import com.spectralogic.ds3client.Ds3Client;
import com.spectralogic.ds3client.commands.PutBucketRequest;
import org.apache.commons.cli.MissingOptionException;

import java.io.IOException;

public class PutBucket extends CliCommand {

    private String bucketName;

    public PutBucket(final Ds3Client client) {
        super(client);
    }

    @Override
    public CliCommand init(final Arguments args) throws Exception {
        bucketName = args.getBucket();
        if (bucketName == null) {
            throw new MissingOptionException("The put bucket command requires '-b' to be set.");
        }
        return this;
    }

    @Override
    public String call() throws Exception {
        try {
            getClient().putBucket(new PutBucketRequest(bucketName)).close();
        }
        catch (final IOException e) {
             return "Error: Request failed with the following error: " + e.getMessage();
        }
        return "Success: Created bucket '"+ bucketName+"'.";
    }
}
