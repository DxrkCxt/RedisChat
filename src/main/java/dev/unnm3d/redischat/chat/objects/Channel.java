package dev.unnm3d.redischat.chat.objects;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Channel extends ChannelAudience {
    @Setter
    private String displayName;
    @Setter
    private String format;
    private final int rateLimit;
    private final int rateLimitPeriod;
    @Setter
    private String discordWebhook;
    private final boolean filtered;
    private final boolean shownByDefault;
    private final boolean permissionEnabled;
    private final String notificationSound;

    @Builder(
            builderClassName = "NewChannelBuilder",
            builderMethodName = "channelBuilder"
    )
    public Channel(String name, String displayName, String format,
                   int proximityDistance, int rateLimit, int rateLimitPeriod,
                   String discordWebhook, boolean filtered, boolean shownByDefault,
                   boolean permissionEnabled, String notificationSound, @Singular List<String> permissions) {
        super(AudienceType.CHANNEL, name, proximityDistance, permissions);
        this.displayName = displayName;
        this.format = format;
        this.rateLimit = rateLimit;
        this.rateLimitPeriod = rateLimitPeriod;
        this.discordWebhook = discordWebhook;
        this.filtered = filtered;
        this.shownByDefault = shownByDefault;
        this.permissionEnabled = permissionEnabled;
        this.notificationSound = notificationSound;
    }

    public static NewChannelBuilder channelBuilder(String name) {
        return new NewChannelBuilder()
                .name(name)
                .displayName(name)
                .rateLimit(5)
                .rateLimitPeriod(3)
                .discordWebhook("")
                .filtered(true)
                .shownByDefault(true)
                .permissionEnabled(true)
                .notificationSound(null);
    }
}
