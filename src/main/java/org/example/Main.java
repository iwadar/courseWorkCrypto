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

    private static long[] getLongFrom128Byte(byte[] initArray)
    {
        byte[] bla = new byte[8];
        byte[] bla1 = new byte[8];
        System.arraycopy(initArray, 0, bla, 0, 8);
        System.arraycopy(initArray, 8, bla1, 0, 8);
        long[] res = {bytesToLong(bla), bytesToLong(bla1)};
        return res;
    }
    private static byte[] longToBytes(long l) {
        byte[] result = new byte[Long.BYTES];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= Byte.SIZE;
        }
        return result;
    }

    private static long bytesToLong(final byte[] b) {
        long result = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            result <<= Byte.SIZE;
            result |= (b[i] & 0xFF);
        }
        return result;
    }

    public static void main(String[] args) {
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
//        Random random = new Random();
//        BigInteger key;
//        do {
//            key = new BigInteger(128, random);
//        } while (key.bitLength() != 128);
//
//        System.out.println(key.toString(10));

//        BigInteger key = new BigInteger("227231978129878989741253583425500098299");
//
//        CamelliaKey k = new CamelliaKey();
//        k.generateKeys(key);
//
//        Camellia algo = new Camellia(k);
////
//        String t = "hello, dasha!";
//        byte[] textByte = t.getBytes();
//        int lengthPadding = 16 - textByte.length % 16;
//        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
//        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
//        for (int i = 0; i < lengthPadding; i++)
//        {
//            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
//        }
//        List<BigInteger> encryptText = new ArrayList<>();
//        List<BigInteger> decryptText = new ArrayList<>();
//
//        for (int i = 0; i < copyInputArrayWithPadding.length; i += 16)
//        {
//            byte[] bla = new byte[16];
//            bla = getArray128(copyInputArrayWithPadding, i);
//            var encrypt = algo.encrypt(bla);
//            encryptText.add(encrypt);
//        }
//
//        for (int i = 0; i < encryptText.size(); i++)
//        {
//            System.out.println(new String(encryptText.get(i).toByteArray()));
//        }
//        System.out.println();
//
//
//        for (int i = 0; i < encryptText.size(); i++)
//        {
//            var decrypt = algo.decrypt(encryptText.get(i));
//            decryptText.add(decrypt);
//        }
//        for (int i = 0; i < decryptText.size(); i++)
//        {
//            System.out.println(new String(decryptText.get(i).toByteArray()));
//        }
//        System.out.println();
//        var dec = algo.decrypt(encrypt);
//        byte[] bla = dec.toByteArray();
//        System.out.println(new String(dec.toByteArray()));

//        CamelliaSBlocks s = new CamelliaSBlocks();
//        System.out.println(s.cycleShift(0xa7, 1));
//        int bla = (int) ((0xa7 << 1) | (0xa7 >> (32 - 1)));
//        System.out.println(bla);

        String key1 = "ssvjkjngslfkjgns";
        CamelliaKey k = new CamelliaKey();
        k.generateKeys(key1);
        Camellia algo = new Camellia(k);

        String t = "hello, dasha!";
        byte[] textByte = t.getBytes();
        int lengthPadding = 16 - textByte.length % 16;
        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
        for (int i = 0; i < lengthPadding; i++)
        {
            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
        }

        long[] encryptText = new long[copyInputArrayWithPadding.length / 16 * 2];
        long[] decryptText = new long[copyInputArrayWithPadding.length / 16 * 2];
//
        int count = 0;
        for (int i = 0; i < copyInputArrayWithPadding.length; i += 16)
        {
            byte[] bla = getArray128(copyInputArrayWithPadding, i);
            long[] Ds = getLongFrom128Byte(bla);

            var encrypt = algo.encrypt(Ds[0], Ds[1]);
            encryptText[count] = encrypt[0];
            encryptText[count + 1] = encrypt[1];
            count += 2;
        }

        for (int i = 0; i < encryptText.length; i++)
        {
            System.out.print(new String(longToBytes(encryptText[i])));
        }
        System.out.println();


        for (int i = 0; i < encryptText.length; i += 2)
        {
            var decrypt = algo.decrypt(encryptText[i], encryptText[i+1]);
            decryptText[i] = decrypt[0];
            decryptText[i + 1] = decrypt[1];
        }
        for (int i = 0; i < decryptText.length; i++)
        {
//            byte[] bla = longToByteArray(decryptText[i]);
            System.out.print(new String(longToBytes(decryptText[i])));
        }
        System.out.println();
    }
}