package apps.xenione.com.demoloader.data.source.in_memory;
/*
Copyright 24/04/2017 Eugeni Josep Senent i Gabriel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.source.DataSource;

public class InMemoryNoteDataSource implements DataSource<Note> {

    private static final List<Note> notes;

    static {
        notes = new ArrayList<Note>() {
            {
                add(new Note.Builder().withId(1).build());
                add(new Note.Builder().withId(2).build());
                add(new Note.Builder().withId(3).build());
            }
        };
    }

    @Override
    public List<Note> getAll() {
        return Collections.unmodifiableList(notes);
    }

    @Override
    public void save(Note user) {
        notes.add(user);
    }

    @Override
    public void update(Note user) {
        notes.add(user);
    }

    @Override
    public void delete(Note user) {
        notes.remove(user);
    }
}
