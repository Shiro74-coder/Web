using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SinhVien.DAL;

namespace SinhVien
{
    public partial class fCapNhatThongTinSV : Form
    {
        private Form mainForm;
        public fCapNhatThongTinSV()
        {
            InitializeComponent();
        }
        public fCapNhatThongTinSV(Form main)
        {
            InitializeComponent();
        }
        private void Form_Load(object sender, EventArgs e)
        {
            btnLuuThongTin.Click += btnLuuThongTin_Click;
        }
        private void fCapNhatThongTinSV_Load(object sender, EventArgs e)
        {

        }

        private void txtMaSV_TextChanged(object sender, EventArgs e)
        {

        }

        private void grbQuanHuyen_Enter(object sender, EventArgs e)
        {

        }

        private void btnQuayLai_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnLuuThongTin_Click(object sender, EventArgs e)
        {
            //    // Lấy dữ liệu từ các ô nhập liệu trên form
            //    string maSV = txtMaSV.Text;
            //    string hoTen = txtHoTen.Text;
            //    string email = txtEmail.Text;
            //    string diDong = txtDiDong.Text;
            //    string quocGia = cboQuocGia.SelectedItem?.ToString();
            //    string tinhThanh = cboTinhThanh.SelectedItem?.ToString();
            //    string quanHuyen = cboQuanHuyen.SelectedItem?.ToString();
            //    string phuong = txtPhuong.Text;
            //    string diaChi = txtDiaChi.Text;
            //    string hoTenLienHe = txtHoTenLienHe.Text;
            //    string diaChiLienHe = txtDiaChiLienHe.Text;
            //    string dienThoaiLienHe = txtDienThoaiLienHe.Text;
            //    string hoTenCha = txtHoTenCha.Text;
            //    string dienThoaiCha = txtDienThoaiCha.Text;
            //    string hoTenMe = txtHoTenMe.Text;
            //    string dienThoaiMe = txtDienThoaiMe.Text;

            //    // Gọi hàm cập nhật dữ liệu
            //    bool ketQua = DBKetNoi.Instance.SuaSinhVien(maSV, hoTen, email, diDong, quocGia, tinhThanh,
            //                                                quanHuyen, phuong, diaChi, hoTenLienHe,
            //                                                diaChiLienHe, dienThoaiLienHe, hoTenCha,
            //                                                dienThoaiCha, hoTenMe, dienThoaiMe);

            //    // Hiển thị thông báo thành công hoặc thất bại
            //    if (ketQua)
            //    {
            //        MessageBox.Show("Cập nhật thông tin thành công!", "Thông báo", MessageBoxButtons.OK, MessageBoxIcon.Information);
            //        LoadData(); // Tải lại dữ liệu để hiển thị thông tin mới
            //    }
            //    else
            //    {
            //        MessageBox.Show("Cập nhật thất bại. Vui lòng kiểm tra lại!", "Lỗi", MessageBoxButtons.OK, MessageBoxIcon.Error);
            //    }
        }
    }
}
