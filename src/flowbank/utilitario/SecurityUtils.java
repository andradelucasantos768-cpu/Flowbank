package flowbank.utilitario;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SecurityUtils {

    private static final String CHAVE = "FlowBank2026Key!";
    private static final String ALGORITMO = "AES";

    public static String criptografar(String senha) {
        try {
            SecretKeySpec chaveSpec = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, chaveSpec);
            byte[] senhaCriptografada = cipher.doFinal(senha.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(senhaCriptografada);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar senha.", e);
        }
    }

    public static String descriptografar(String senhaCriptografada) {
        try {
            SecretKeySpec chaveSpec = new SecretKeySpec(CHAVE.getBytes("UTF-8"), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, chaveSpec);
            byte[] senhaBytes = cipher.doFinal(Base64.getDecoder().decode(senhaCriptografada));
            return new String(senhaBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar senha.", e);
        }
    }

    public static boolean checkPassword(String senhaDigitada, String senhaCriptografada) {
        return descriptografar(senhaCriptografada).equals(senhaDigitada);
    }
}
