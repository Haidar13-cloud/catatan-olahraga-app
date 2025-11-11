
package com.example.catatanolahragaapp.controller;



import com.example.catatanolahragaapp.model.CatatanOlahraga;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class CatatanOlahragaController implements Initializable {

    @FXML
    private DatePicker dpTanggal;
    @FXML
    private ComboBox<String> cbJenisOlahraga;  // Komponen UI tambahan: ComboBox
    @FXML
    private Spinner<Integer> spDurasi;  // Spinner untuk durasi (bisa dianggap sebagai eksperimen UI)
    @FXML
    private TableView<CatatanOlahraga> tvCatatan;
    @FXML
    private TableColumn<CatatanOlahraga, LocalDate> colTanggal;
    @FXML
    private TableColumn<CatatanOlahraga, String> colJenisOlahraga;
    @FXML
    private TableColumn<CatatanOlahraga, Integer> colDurasi;

    private ObservableList<CatatanOlahraga> dataCatatan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataCatatan = FXCollections.observableArrayList();
        
        // Tambahkan data dummy awal
        dataCatatan.add(new CatatanOlahraga(LocalDate.of(2023, 10, 1), "Lari", 30));
        dataCatatan.add(new CatatanOlahraga(LocalDate.of(2023, 10, 2), "Berenang", 45));
        
        // Setup ComboBox dengan opsi jenis olahraga
        cbJenisOlahraga.getItems().addAll("Lari", "Berenang", "Yoga", "Sepeda", "Angkat Beban");
        
        // Setup Spinner untuk durasi (0-120 menit)
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 120, 30);
        spDurasi.setValueFactory(valueFactory);
        
        // Setup TableView Columns
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colJenisOlahraga.setCellValueFactory(new PropertyValueFactory<>("jenisOlahraga"));
        colDurasi.setCellValueFactory(new PropertyValueFactory<>("durasi"));
        
        // Bind data ke TableView
        tvCatatan.setItems(dataCatatan);
        
        // Tambahkan Listener untuk menampilkan detail saat baris dipilih
        tvCatatan.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showCatatanDetails(newValue));
    }

    /**
     * Listener: Menampilkan data Catatan yang dipilih ke kolom input.
     */
    private void showCatatanDetails(CatatanOlahraga catatan) {
        if (catatan != null) {
            dpTanggal.setValue(catatan.getTanggal());
            cbJenisOlahraga.setValue(catatan.getJenisOlahraga());
            spDurasi.getValueFactory().setValue(catatan.getDurasi());
        } else {
            dpTanggal.setValue(null);
            cbJenisOlahraga.setValue(null);
            spDurasi.getValueFactory().setValue(30);  // Reset ke default
        }
    }

    @FXML
    private void handleAddCatatan(ActionEvent event) {
        LocalDate tanggal = dpTanggal.getValue();
        String jenis = cbJenisOlahraga.getValue();
        Integer durasi = spDurasi.getValue();

        if (tanggal == null || jenis == null || durasi == null || durasi <= 0) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Tanggal, Jenis Olahraga, dan Durasi harus diisi dengan benar.");
            return;
        }

        CatatanOlahraga baru = new CatatanOlahraga(tanggal, jenis, durasi);
        dataCatatan.add(baru);
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Catatan olahraga berhasil ditambahkan.");
    }

    @FXML
    private void handleEditCatatan(ActionEvent event) {
        CatatanOlahraga selectedCatatan = tvCatatan.getSelectionModel().getSelectedItem();

        if (selectedCatatan != null) {
            // Update object model menggunakan setter
            selectedCatatan.setTanggal(dpTanggal.getValue());
            selectedCatatan.setJenisOlahraga(cbJenisOlahraga.getValue());
            selectedCatatan.setDurasi(spDurasi.getValue());

            tvCatatan.refresh();
            
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Catatan olahraga berhasil diubah.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih catatan yang ingin diubah.");
        }
    }

    @FXML
    private void handleDeleteCatatan(ActionEvent event) {
        CatatanOlahraga selectedCatatan = tvCatatan.getSelectionModel().getSelectedItem();

        if (selectedCatatan != null) {
            dataCatatan.remove(selectedCatatan);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Catatan olahraga berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih catatan yang ingin dihapus.");
        }
    }

    // Utilitas
    private void clearFields() {
        dpTanggal.setValue(null);
        cbJenisOlahraga.setValue(null);
        spDurasi.getValueFactory().setValue(30);
        tvCatatan.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}