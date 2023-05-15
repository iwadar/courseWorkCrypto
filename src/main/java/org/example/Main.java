package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static byte[] getArray128(byte[] initArray, int startIndex)
    {
        byte[] copy = new byte[16];
        System.arraycopy(initArray, startIndex, copy, 0, 16);
        return copy;
    }

    public static void main(String[] args)
    {
        System.out.println("Hello world!");
//        BigInteger n = new BigInteger("4");
//        int r = 50;
//        ElgamalPublicKey k = new ElgamalPublicKey();
//        k.setP(BigInteger.valueOf();
//        System.out.println(k.generateParamG(BigInteger.valueOf(13)));
//        System.out.println(new SimplicityTest().testMillerRabin(n, r));
//        Random random = new Random();
//        BigInteger bigInteger = new BigInteger(20, random);
//        System.out.println("Random BigInteger: " + bigInteger);

//        String text = "Pizdec, nahuy, blyut!";
//        ElgamalKey k = new ElgamalKey();
//        k.generateKey();
//        ElgamalEncrypt encrypt = new ElgamalEncrypt(k);

//        byte[] textByte = text.getBytes();
//        int lengthPadding = 8 - textByte.length % 8;
//        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
//        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
//        for (int i = 0; i < lengthPadding; i++)
//        {
//            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
//        }

//        var decryptText = encrypt.encrypt(textByte);
//        for (int i = 0; i< decryptText.length; i++)
//        {
//            System.out.print(new String(decryptText[i].toByteArray()));
//        }
//        System.out.println();
//
//        var newOldText = encrypt.decrypt(decryptText);
//
//        for (int i = 0; i< newOldText.length; i++)
//        {
//            System.out.print(new String(newOldText[i].toByteArray()));
//        }
//        System.out.println();
        Random random = new Random();
        BigInteger key = new BigInteger(128, random);

//        BigInteger key = new BigInteger("340282366920938463463374607431768211445");

        CamelliaKey k = new CamelliaKey();
        k.generateKeys(key);

        Camellia algo = new Camellia(k);
//
        String t = "hello, dasha!";
        byte[] textByte = t.getBytes();
        int lengthPadding = 16 - textByte.length % 16;
        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
        for (int i = 0; i < lengthPadding; i++)
        {
            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
        }
        List<BigInteger> encryptText = new ArrayList<>();
        List<BigInteger> decryptText = new ArrayList<>();

        for (int i = 0; i < copyInputArrayWithPadding.length; i += 16)
        {
            byte[] bla = new byte[16];
            bla = getArray128(copyInputArrayWithPadding, i);
            var encrypt = algo.encrypt(bla);
            encryptText.add(encrypt);
        }

        for (int i = 0; i < encryptText.size(); i++)
        {
            System.out.println(new String(encryptText.get(i).toByteArray()));
        }
        System.out.println();


        for (int i = 0; i < encryptText.size(); i++)
        {
            var decrypt = algo.decrypt(encryptText.get(i));
            decryptText.add(decrypt);
        }
        for (int i = 0; i < decryptText.size(); i++)
        {
            System.out.println(new String(decryptText.get(i).toByteArray()));
        }
        System.out.println();
//        var dec = algo.decrypt(encrypt);
//        byte[] bla = dec.toByteArray();
//        System.out.println(new String(dec.toByteArray()));
    }
}