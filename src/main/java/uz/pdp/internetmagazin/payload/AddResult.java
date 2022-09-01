package uz.pdp.internetmagazin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import uz.pdp.internetmagazin.entity.Photo;

@Data
@AllArgsConstructor
public class AddResult {

    private boolean success;

    private Photo photo;


}
