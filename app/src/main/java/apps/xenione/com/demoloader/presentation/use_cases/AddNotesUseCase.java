package apps.xenione.com.demoloader.presentation.use_cases;
/*
Copyright 29/04/2017 Eugeni Josep Senent i Gabriel

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

import apps.xenione.com.demoloader.data.Note;
import apps.xenione.com.demoloader.data.Note.Builder;
import apps.xenione.com.demoloader.data.NoteRepository;

public class AddNotesUseCase extends UseCase<Note, Void> {

    private NoteRepository mRepository;
    Note mNote;

    public AddNotesUseCase(NoteRepository repository) {
        mRepository = repository;
    }

    @Override
    public Void call() throws Exception {
        mRepository.save(mNote);
        return null;
    }

    @Override
    public NoteParamBuilder getParamBuilder() {
        return new NoteParamBuilder(this);
    }

    public static class NoteParamBuilder extends ParamBuilder<Note> {

        private String mBody;
        private String mTitle;

        NoteParamBuilder(AddNotesUseCase useCase) {
            super(useCase);
        }

        public NoteParamBuilder withBody(String body) {
            this.mBody = body;
            return this;
        }

        public NoteParamBuilder WithTitle(String title) {
            this.mTitle = title;
            return this;
        }

        @Override
        protected Note buildParams() {
            return new Builder().withBody(mBody).withTitle(mTitle).build();
        }
    }
}
