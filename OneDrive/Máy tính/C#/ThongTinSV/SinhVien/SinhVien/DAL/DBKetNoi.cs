using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SinhVien.DAL
{
    public partial class DBKetNoi : Component
    {
        private static DBKetNoi instance;
        private ThongTinSVDataContext context;
        public static DBKetNoi Instance
        {
            get
            {
                if (instance == null)
                    instance = new DBKetNoi();
                return instance;
            }
        }
        public DBKetNoi()
        {
            InitializeComponent();
            context = new ThongTinSVDataContext();
        }
        public DBKetNoi(IContainer container)
        {
            container.Add(this);
            InitializeComponent();
            context = new ThongTinSVDataContext();
        }
        public List<Thong_Tin_Sinh_Vien> GetDanhSachSinhVien()
        {
            return context.Thong_Tin_Sinh_Viens.ToList();
        }
        //Thêm
        public bool ThemSinhVien(Thong_Tin_Sinh_Vien sv)
        {
            try
            {
                context.Thong_Tin_Sinh_Viens.InsertOnSubmit(sv);
                context.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }

        // Sửa thông tin sinh viên
        public bool SuaSinhVien(
                                string maSV, string hoTen, string email, string diDong, string quocGia,
                                string tinhThanh, string quanHuyen, string phuong, string diaChi,
                                string hoTenLienHe, string diaChiLienHe, string dienThoaiLienHe,
                                string hoTenCha, string dienThoaiCha, string hoTenMe, string dienThoaiMe)
        {
            try
            {
                // Tìm sinh viên theo MaSV
                var sinhVien = context.Thong_Tin_Sinh_Viens.FirstOrDefault(s => s.MaSV == maSV);
                if (sinhVien == null) return false;

                // Cập nhật thông tin sinh viên
                sinhVien.HoTen = hoTen;

                // Tìm thông tin liên lạc của sinh viên
                var lienLac = context.Thong_Tin_Lien_Lacs.FirstOrDefault(ll => ll.MaSV == maSV);
                if (lienLac != null)
                {
                    lienLac.Email = email;
                    lienLac.DiDong = diDong;
                    lienLac.QuocGia = quocGia;
                    lienLac.TinhThanh = tinhThanh;
                    lienLac.QuanHuyen = quanHuyen;
                    lienLac.Phuong = phuong;
                    lienLac.DiaChi = diaChi;
                }

                // Tìm thông tin người liên hệ
                var nguoiLH = context.Thong_Tin_Nguoi_LHs.FirstOrDefault(nlh => nlh.MaSV == maSV);
                if (nguoiLH != null)
                {
                    nguoiLH.HoTenLienHe = hoTenLienHe;
                    nguoiLH.DiaChiLienHe = diaChiLienHe;
                    nguoiLH.DienThoaiLienHe = dienThoaiLienHe;
                    nguoiLH.HoTenCha = hoTenCha;
                    nguoiLH.DienThoaiCha = dienThoaiCha;
                    nguoiLH.HoTenMe = hoTenMe;
                    nguoiLH.DienThoaiMe = dienThoaiMe;
                }

                // Lưu thay đổi vào database
                context.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
        // Xóa sinh viên
        public bool XoaSinhVien(string maSV)
        {
            try
            {
                var sinhVien = context.Thong_Tin_Sinh_Viens.FirstOrDefault(s => s.MaSV == maSV);
                if (sinhVien == null) return false;

                context.Thong_Tin_Sinh_Viens.DeleteOnSubmit(sinhVien);
                context.SubmitChanges();
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
