package devoxx.microframeworks.services;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTSigner.Options;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationRoute {

    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Logger LOG = LoggerFactory.getLogger(AuthenticationRoute.class);
    private static final byte[] SEED = new SecureRandom().generateSeed(16);
    public static final int EXPIRY_SECONDS = (int) (Duration.ofDays(1).toMinutes() * 60);
    private static final JWTSigner JWT_SIGNER = new JWTSigner(SEED);
    public static final JWTVerifier JWT_VERIFIER = new JWTVerifier(SEED);

    public Object handleGetUser(Request request, Response response) {
        try {
            String token = request.params("token");
            LOG.info("Check token {}", token);
            Map<String, Object> map = JWT_VERIFIER.verify(token);
            map.remove("exp");
            return GSON.toJson(map);
        } catch (IOException | JWTVerifyException | GeneralSecurityException e) {
            throw new SecurityException("Invalid JWT", e);
        }
    }

    public Object handleAuthenticate(Request request, Response response) {
        try (ByteArrayInputStream input = new ByteArrayInputStream(request.bodyAsBytes());
             Reader reader = new InputStreamReader(input)) {
            Login login = GSON.fromJson(reader, Login.class);
            if (login == null) {
                throw new SecurityException("No body");
            }
            LOG.info("Login {}", login);
            if (!this.check(login)) {
                throw new SecurityException("Invalid login/password");
            }
            // Create JWT
            Map<String, Object> map = new HashMap<>();
            map.put("email", login.getEmail());
            map.put("name", login.getEmail()); // TODO extract name
            Options options = new Options().setExpirySeconds(EXPIRY_SECONDS);
            String jwt = JWT_SIGNER.sign(map, options);
            return GSON.toJson(Collections.singletonMap("token", jwt));
        } catch (JsonParseException | IOException e) {
            throw new IllegalArgumentException("Invalid body", e);
        }
    }

    private boolean check(Login login) {
        // XXX very unsecured check for demo purpose !
        return Arrays.asList("admin", "plop").contains(login.getPassword());
    }
}
