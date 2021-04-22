package pl.example.zegarszachowy;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimeViewModel  extends ViewModel {
        private MutableLiveData<Boolean> czy_dziala;
        public MutableLiveData<Boolean> getCzyDziala() {
            if (czy_dziala == null) {
                czy_dziala = new MutableLiveData<Boolean>();
            }
            return czy_dziala;
        }

        public void setCzy_dziala(Boolean czy_dziala) {
            this.czy_dziala.setValue(czy_dziala);
        }
        public int licznikA=0;

}

