package com.craftmend.openaudiomc.spigot.modules.speakers.objects;

import com.craftmend.openaudiomc.OpenAudioMc;
import com.craftmend.openaudiomc.generic.database.internal.DataStore;
import com.craftmend.openaudiomc.spigot.modules.speakers.SpeakerService;
import com.craftmend.openaudiomc.spigot.modules.speakers.enums.ExtraSpeakerOptions;
import com.craftmend.openaudiomc.spigot.modules.speakers.enums.SpeakerType;
import com.craftmend.storm.api.markers.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
public class Speaker extends DataStore {

    @Column @Getter private String source;
    @Column @Getter private UUID speakerId;
    @Column @Setter @Getter private Integer radius;

    @Column(
            storeAsBlob = true
    )
    @Getter private MappedLocation location;

    @Column(
            storeAsBlob = true
    )
    @Setter @Getter private SpeakerType speakerType;

    @Column(
            storeAsBlob = true
    )
    @Getter private Set<ExtraSpeakerOptions> extraOptions = new HashSet<>();

    @Getter @Setter private Boolean validated = false;


    public Speaker(String source, UUID id, int radius, MappedLocation location, SpeakerType speakerType, EnumSet<ExtraSpeakerOptions> options) {
        this.source = source;
        this.speakerId = id;
        this.radius = radius;
        this.location = location;
        this.speakerType = speakerType;
        this.extraOptions = options;
    }

    public SpeakerMedia getMedia() {
        return OpenAudioMc.getService(SpeakerService.class).getMedia(source);
    }

}
