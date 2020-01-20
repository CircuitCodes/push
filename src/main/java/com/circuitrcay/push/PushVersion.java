package com.circuitrcay.push;

public class PushVersion {
    public static String MAJOR = "0";
    public static String MINOR = "0";
    public static String PATCH = "1";
    public static PushReleaseType RELEASE_TYPE = PushReleaseType.PreAlpha;
    public static String VERSION = String.format("%s.%s.%s-%s", MAJOR, MINOR, PATCH, RELEASE_TYPE.code);
}

enum PushReleaseType {
    PreAlpha("pa"),
    Alpha("a"),
    Beta("b"),
    Stable("rel");

    public String code;
    PushReleaseType(String code) {
        this.code = code;
    }
}
