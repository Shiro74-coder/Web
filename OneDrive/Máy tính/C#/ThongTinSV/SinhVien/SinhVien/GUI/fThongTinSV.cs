using System.Windows.Forms;

namespace SinhVien
{
    public partial class fThongTinSV : Form
    {
        public fThongTinSV()
        {
            InitializeComponent();
        }
        private Form mainForm;

        
        private Form activeForm = null;
        public void OpenCapNhatThongTinSV(Form CapNhatThongTinSVForm)
        {
            if (activeForm != null) 
                activeForm.Close();
            activeForm = CapNhatThongTinSVForm;
            CapNhatThongTinSVForm.TopLevel = false;
            CapNhatThongTinSVForm.FormBorderStyle = FormBorderStyle.None;
            CapNhatThongTinSVForm.Dock = DockStyle.Fill;
            panel_main.Controls.Add(CapNhatThongTinSVForm);
            panel_main.Tag = CapNhatThongTinSVForm;
            CapNhatThongTinSVForm.BringToFront();
            CapNhatThongTinSVForm.Show();
        }

        private void fThongTinSV_Load(object sender, System.EventArgs e)
        {

        }

        private void btnCapNhatThongTinCaNhan_Click(object sender, System.EventArgs e)
        {
            OpenCapNhatThongTinSV(new fCapNhatThongTinSV(this));
             
        }
    }
}
