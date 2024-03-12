CREATE TABLE phonghoc (
    MaPh CHAR(10) PRIMARY KEY,
    TenPh VARCHAR(50),
    Succhua INT
);

CREATE TABLE giaovien (
    MaGV CHAR(10) PRIMARY KEY,
    TenGV VARCHAR(50),
    NgaySinh DATE,
    DiaChi VARCHAR(100),
    SDT VARCHAR(15),
    SoNawmCongTac INT,
    Username VARCHAR(20),
    Password VARCHAR(12)
);

CREATE TABLE dungcu (
    MaDC CHAR(10) PRIMARY KEY,
    TenDC VARCHAR(50),
    MaPh CHAR(10),
    SoLuong INT,
    FOREIGN KEY (MaPh) REFERENCES phonghoc(MaPh)
);

CREATE TABLE hoadon (
    MaHD CHAR(10) PRIMARY KEY,
    NgayLap DATE,
    MaDC CHAR(10),
    SoLuong INT,
    ThanhTien INT,
    FOREIGN KEY (MaDC) REFERENCES dungcu(MaDC)
);

CREATE TABLE log (
    LogID INT AUTO_INCREMENT PRIMARY KEY,
    MaUser CHAR(10),
    Noidung VARCHAR(255),
    ThoiGian TIMESTAMP,
    FOREIGN KEY (MaUser) REFERENCES taikhoan(MaUser)
);

CREATE TABLE lophoc (
    MaLop CHAR(10) PRIMARY KEY,
    TenLop VARCHAR(50),
    NienKhoa VARCHAR(15),
    Siso INT
);

CREATE TABLE lichthuchanh (
    MaPh CHAR(10),
    ThoiGian VARCHAR(60),
    MaLop CHAR(10),
    Ngay DATE,
    PRIMARY KEY (MaPh, Ngay, ThoiGian),
    FOREIGN KEY (MaPh) REFERENCES phonghoc(MaPh),
    FOREIGN KEY (MaLop) REFERENCES lophoc(MaLop)
);

CREATE TABLE taikhoan (
    MaUser CHAR(10) PRIMARY KEY,
    Username VARCHAR(25),
    Password VARCHAR(20),
    LoaiTK VARCHAR(10)
);
