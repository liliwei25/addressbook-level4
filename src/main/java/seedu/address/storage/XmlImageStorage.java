package seedu.address.storage;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

//@@author liliwei25
/**
 * Creates folder to store all images saved by user
 */
public class XmlImageStorage {

    private static final String PNG = ".png";

    /**
     * Save selected image to image folder
     *
     * @throws IOException when image copy fails
     */
    public void saveImage(File image, String name) throws IOException {
        requireNonNull(image);
        requireNonNull(name);

        File file = new File(name.concat(PNG));
        Files.copy(image.toPath(), file.toPath(), REPLACE_EXISTING);
    }
}
