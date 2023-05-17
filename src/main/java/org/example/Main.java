package org.example;

import org.example.camellia.Camellia;
import org.example.camellia.CamelliaKey;
import org.example.elgamal.ElgamalEncrypt;
import org.example.elgamal.ElgamalKey;
import org.example.elgamal.ElgamalPublicKey;

import java.util.Random;
import java.util.stream.Collectors;

import static org.example.HelpFunction.*;

public class Main {


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

//        String key1 = "ajshklfshxt5jjhslijhpi48";
//
//        CamelliaKey k = new CamelliaKey();
//        k.generateKeys(key1);
//
//        Camellia algo = new Camellia(k);
//
//        String t = "hello, dasha!  I love Korol and Shut!\n WOW! I love Gorshok soooooo much too!!!";
//
//        byte[] textByte = t.getBytes();
//        int lengthPadding = 16 - textByte.length % 16;
//        byte[] copyInputArrayWithPadding = new byte[textByte.length + lengthPadding];
//        System.arraycopy(textByte, 0, copyInputArrayWithPadding, 0, textByte.length);
//        for (int i = 0; i < lengthPadding; i++)
//        {
//            copyInputArrayWithPadding[textByte.length + i] = (byte)lengthPadding;
//        }
//
//        long[] encryptText = new long[copyInputArrayWithPadding.length / 16 * 2];
////        byte[] byteEncryptText = new byte[]
//
//        int count = 0;
//        for (int i = 0; i < copyInputArrayWithPadding.length; i += 16)
//        {
//            byte[] bla = getArray128(copyInputArrayWithPadding, i);
//            long[] Ds = getLongFrom128Byte(bla);
//
//            var encrypt = algo.encrypt(Ds[0], Ds[1]);
//            System.arraycopy(longToBytes(encrypt[0]), 0, copyInputArrayWithPadding, i, 8);
//            System.arraycopy(longToBytes(encrypt[1]), 0, copyInputArrayWithPadding, i + Long.BYTES, 8);
//
////            encryptText[count] = encrypt[0];
////            encryptText[count + 1] = encrypt[1];
////            count += 2;
//        }

//        for (int i = 0; i < encryptText.length; i++)
//        {
//            System.out.print(new String(longToBytes(encryptText[i])));
//        }
//        System.out.println(new String(copyInputArrayWithPadding));

//        byte[] decryptTEXTTTTTTTTTT = new byte[encryptText.length * Long.BYTES];
//        for (int i = 0; i < encryptText.length; i += 2)
//        {
//            var decrypt = algo.decrypt(encryptText[i], encryptText[i+1]);
//            System.arraycopy(longToBytes(decrypt[0]), 0, decryptTEXTTTTTTTTTT, i * Long.BYTES, 8);
//            System.arraycopy(longToBytes(decrypt[1]), 0, decryptTEXTTTTTTTTTT, (i + 1) * Long.BYTES, 8);
//        }
//        decryptTEXTTTTTTTTTT = deletePadding(decryptTEXTTTTTTTTTT);
//        System.out.println(new String(decryptTEXTTTTTTTTTT));

//        for (int i = 0; i < copyInputArrayWithPadding.length; i += 16)
//        {
//            byte[] bla = getArray128(copyInputArrayWithPadding, i);
//            long[] Ds = getLongFrom128Byte(bla);
//
//            var encrypt = algo.decrypt(Ds[0], Ds[1]);
//            System.arraycopy(longToBytes(encrypt[0]), 0, copyInputArrayWithPadding, i, 8);
//            System.arraycopy(longToBytes(encrypt[1]), 0, copyInputArrayWithPadding, i + Long.BYTES, 8);
//
////            encryptText[count] = encrypt[0];
////            encryptText[count + 1] = encrypt[1];
////            count += 2;
//        }
//                copyInputArrayWithPadding = deletePadding(copyInputArrayWithPadding);
//
//        System.out.println(new String(copyInputArrayWithPadding));


//        ------------------------- MODES TRAIN ---------------------------------------------
        String key1 = "ajshklfshxt5jjhslijhpi48";
//
        CamelliaKey k = new CamelliaKey();
//        k.generateKeys(key1);

        k.generateKeys(generateRandomString(32));
//
        Camellia algo = new Camellia(k);
//
        String t = "hello, dasha! I love Korol and Shut!\nWOW! I love Gorshok soooooo much too!!!\nKak ge djoker you xiter";

        byte[] textByte = t.getBytes();
//        ECBMode m = new ECBMode(algo);

        RDMode m = new RDMode(algo, generateRandomString(16).getBytes());
        byte[] encr = m.encrypt(textByte);
        System.out.println(new String(encr));
        byte[] decr = m.decrypt(encr);

        System.out.println(new String(decr));

    }
}