package apps.xenione.com.demoloader.cuore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Eugeni on 08/10/2016.
 */
public class NoteRepositoryImpl implements NoteRepository {

    private static List<Note> NOTES = new ArrayList<Note>() {{
        add(new Note("Tres de pego", "Fins ací hem arribat els que veníem del poble de Pego. " +
                "I no sé res dels meus companys però he perdut tota esperança, " +
                "ja res més puc esperar."));
        add(new Note("Farem saó", "El tio Pep, els quintos d\'Alzira i els guerrers de Moixent " +
                "El Rat Penat, el moro Muza, el tio Canya I Rosariet la carnissera de la Xara " +
                "La Dama d\'Elx, la Delicà de Gandia i el Miquelet de la Seu"));
        add(new Note("Quina calitja", "S\'han acabat tardors i primaveres, i els tarongers floreixen " +
                "pel febrer. Puja la mar i trenca les barreres, i el temps que ens queda " +
                "s\'acaba, s\'acaba, s\'acab."));
    }};

    private static Comparator<Note> DATE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note lhs, Note rhs) {
            return (int) (lhs.mCreateDate - rhs.mCreateDate);
        }
    };

    @Override
    public List<Note> getAllOrderByDate() {
        Collections.sort(NOTES, DATE_COMPARATOR);
        return NOTES;
    }

    @Override
    public boolean save(Note note) {
        return NOTES.add(note);
    }

}
