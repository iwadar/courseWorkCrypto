package org.example;

import java.math.BigInteger;

public class Main {
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

        String text = "Pizdec, nahuy, blyut!";
        ElgamalKey k = new ElgamalKey();
        k.generateKey();
        ElgamalEncrypt encrypt = new ElgamalEncrypt(k);

        byte[] textByte = text.getBytes();
        int lengthPadding = 8 - textByte.length % 8;
        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
        for (int i = 0; i < lengthPadding; i++)
        {
            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
        }

        var decryptText = encrypt.encrypt(textByte);
        for (int i = 0; i< decryptText.length; i++)
        {
            System.out.print(new String(decryptText[i].toByteArray()));
        }
        System.out.println();

        var newOldText = encrypt.decrypt(decryptText);
//        System.out.println(decryptText.toString());
//
//        System.out.println(newOldText.toString());

        for (int i = 0; i< newOldText.length; i++)
        {
            System.out.print(new String(newOldText[i].toByteArray()));
        }
        System.out.println();

//        BigInteger paddingLength = newOldText[newOldText.length-1];
//        for (int i )
//        System.arraycopy(input, 0, tmp, 0, tmp.length);


    }
}