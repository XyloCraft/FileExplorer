package de.asedem.fileexplorer.config.model;

import de.rytrox.spicy.config.UTFConfig;
import org.jetbrains.annotations.NotNull;

public record Config(
        @NotNull ModSupport modSupport
) {

    public Config(@NotNull UTFConfig utfConfig) {
        this(
                new ModSupport(utfConfig)
        );
    }
}
