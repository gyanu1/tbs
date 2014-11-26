/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs490.tbs.services;

/**
 *
 * @author PuKhanal
 */
public class ReaderFactory {

    public static ExcelReader getReader(ReaderType type) {
        switch (type) {
            case call_details:
                return new CallDetailsReader();
            case rate:
                return new RateReader();
            case calling_code:
                return new CallingCodeReader();
        }
        throw new IllegalArgumentException("Reader type undefined.");
    }
}
