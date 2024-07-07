package lk.ijse.Naturab.Model.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class ClientTm {
    private String CId;
    private String Name;
    private String Tel;
    private String Email;
    private JFXButton btndelete;
    private JFXButton btnedit;

}
