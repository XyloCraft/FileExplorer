package de.asedem.fileexplorer.config.model;

import de.rytrox.spicy.config.UTFConfig;
import org.jetbrains.annotations.NotNull;

public record ModSupport(
    boolean enabled,
    boolean fileUpload,
    boolean fileDownload
) {
    public ModSupport(@NotNull UTFConfig utfConfig) {
        this(
                utfConfig.getBoolean("mod-support.enabled", false),
                utfConfig.getBoolean("mod-support.file-upload", true),
                utfConfig.getBoolean("mod-support.file-download", true)
        );
    }
}
