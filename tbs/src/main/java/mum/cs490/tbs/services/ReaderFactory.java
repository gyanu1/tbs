/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

import mum.cs490.tbs.model.CallDetail;
import mum.cs490.tbs.utility.FileType;

/**
 *
 * @author PuKhanal
 */
public class ReaderFactory {

    public static ExcelReader getReader(FileType type) {
        switch (type) {
            case CALL_DETAIL:
                return new CallDetailsReader();
            case RATE:
                return new RateReader();
        }
        throw new IllegalArgumentException("Reader type undefined.");
    }
}
