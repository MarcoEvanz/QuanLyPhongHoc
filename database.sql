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
DELIMITER //
CREATE PROCEDURE InsertPhongHoc(
    IN phonghoc_MaPh CHAR(10),
    IN phonghoc_TenPh VARCHAR(50),
    IN phonghoc_Succhua INT,
    IN maUser CHAR(10)
)
BEGIN
    INSERT INTO phonghoc (MaPh, TenPh, Succhua) VALUES (phonghoc_MaPh, phonghoc_TenPh, phonghoc_Succhua);
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Inserted a new PhongHoc: ', phonghoc_MaPh), NOW());
END //
DELIMITER ;
-- updatePhongHoc
DELIMITER //
CREATE PROCEDURE UpdatePhongHoc(
    IN phonghoc_MaPh CHAR(10),
    IN phonghoc_TenPh VARCHAR(50),
    IN phonghoc_Succhua INT,
    IN maUser CHAR(10)
)
BEGIN
    UPDATE phonghoc SET TenPh = phonghoc_TenPh, Succhua = phonghoc_Succhua WHERE MaPh = phonghoc_MaPh;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Updated PhongHoc: ', phonghoc_MaPh), NOW());
END //

-- deletePhongHoc
DELIMITER //
CREATE PROCEDURE DeletePhongHoc(
    IN maPh CHAR(10),
    IN maUser CHAR(10)
)
BEGIN
    DELETE FROM phonghoc WHERE MaPh = maPh;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Deleted PhongHoc: ', maPh), NOW());
END //

-- insertGiaoVien
DELIMITER //
CREATE PROCEDURE InsertGiaoVien(
    IN gv_MaGV CHAR(10),
    IN gv_TenGV VARCHAR(50),
    IN gv_NgaySinh DATE,
    IN gv_DiaChi VARCHAR(100),
    IN gv_SDT VARCHAR(15),
    IN gv_SoNamCongTac INT,
    IN gv_Username VARCHAR(20),
    IN gv_Password VARCHAR(12),
    IN maUser CHAR(10)
)
BEGIN
    INSERT INTO giaovien (MaGV, TenGV, NgaySinh, DiaChi, SDT, SoNamCongTac, Username, Password) 
    VALUES (gv_MaGV, gv_TenGV, gv_NgaySinh, gv_DiaChi, gv_SDT, gv_SoNamCongTac, gv_Username, gv_Password);
    
    -- Insert into the log table
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Inserted a new GiaoVien: ', gv_MaGV), NOW());
END //

-- updateGiaoVien
DELIMITER //
CREATE PROCEDURE UpdateGiaoVien(
    IN gv_MaGV CHAR(10),
    IN gv_TenGV VARCHAR(50),
    IN gv_NgaySinh DATE,
    IN gv_DiaChi VARCHAR(100),
    IN gv_SDT VARCHAR(15),
    IN gv_SoNamCongTac INT,
    IN gv_Username VARCHAR(20),
    IN gv_Password VARCHAR(12),
    IN maUser CHAR(10)
)
BEGIN
    UPDATE giaovien SET TenGV = gv_TenGV, NgaySinh = gv_NgaySinh, DiaChi = gv_DiaChi, SDT = gv_SDT, 
    SoNamCongTac = gv_SoNamCongTac, Username = gv_Username, Password = gv_Password WHERE MaGV = gv_MaGV;
    
    -- Insert into the log table
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Updated GiaoVien: ', gv_MaGV), NOW());
END //

-- deleteGiaoVien
DELIMITER //
CREATE PROCEDURE DeleteGiaoVien(
    IN maGV CHAR(10),
    IN maUser CHAR(10)
)
BEGIN
    DELETE FROM giaovien WHERE MaGV = maGV;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Deleted GiaoVien: ', maGV), NOW());
END //

