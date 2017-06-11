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
import apps.xenione.com.demoloader.data.NoteRepository;

public class SetFavoriteNotesUseCase extends UseCase<SetFavoriteNotesUseCase.SetFavoriteInput, Void> {

    private NoteRepository mRepository;

    public static class SetFavoriteInput {
        boolean favorite;

        Note note;
    }

    public SetFavoriteNotesUseCase(NoteRepository repository) {
        mRepository = repository;
    }

    @Override
    public Void call() throws Exception {
        Thread.sleep(5 * 1000);
        if (param.favorite) {
            mRepository.setFavorite(param.note);
        } else {
            mRepository.unsetFavorite(param.note);
        }
        return null;
    }

    @Override
    public SetFavoriteParamBuilder getParamBuilder() {
        return new SetFavoriteParamBuilder(this) ;
    }

    public static class SetFavoriteParamBuilder extends ParamBuilder<SetFavoriteInput> {

        private boolean favorite;
        private Note note;

        public SetFavoriteParamBuilder(UseCase<SetFavoriteInput, ?> useCase) {
            super(useCase);
        }

        public SetFavoriteParamBuilder withFavorite(boolean favorite) {
            this.favorite = favorite;
            return this;
        }

        public SetFavoriteParamBuilder withNote(Note note) {
            this.note = note;
            return this;
        }

        @Override
        protected SetFavoriteInput buildParams() {
            SetFavoriteInput input = new SetFavoriteInput();
            input.favorite = favorite;
            input.note = note;
            return input;
        }
    }
}