package apps.xenione.com.demoloader.data.source.local;
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

import com.google.gson.Gson;

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.source.NoteMapper;

public class LocalNoteMapper implements NoteMapper<String> {

    private Gson mGson;

    public LocalNoteMapper(Gson gson) {
        mGson = gson;
    }

    @Override
    public Note from(String from) {
        return mGson.fromJson(from, Note.class);
    }

    @Override
    public String to(Note note) {
        return mGson.toJson(note);
    }
}
