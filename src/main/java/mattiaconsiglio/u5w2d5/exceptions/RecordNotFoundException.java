package mattiaconsiglio.u5w2d5.exceptions;

import java.util.UUID;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String recordType, UUID id) {
        super(recordType + " with id " + id + " not found.");
    }
}
