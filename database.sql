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
DELIMITER //
CREATE PROCEDURE InsertDungCu(
    IN dungcu_MaDC CHAR(10),
    IN dungcu_TenDC VARCHAR(50),
    IN dungcu_MaPh CHAR(10),
    IN dungcu_SoLuong INT,
    IN maUser CHAR(10)
)
BEGIN
    INSERT INTO dungcu (MaDC, TenDC, MaPh, SoLuong) VALUES (dungcu_MaDC, dungcu_TenDC, dungcu_MaPh, dungcu_SoLuong);
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Inserted a new DungCu: ', dungcu_TenDC), NOW());
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE UpdateDungCu(
    IN dungcu_MaDC CHAR(10),
    IN dungcu_TenDC VARCHAR(50),
    IN dungcu_MaPh CHAR(10),
    IN dungcu_SoLuong INT,
    IN maUser CHAR(10)
)
BEGIN
    UPDATE dungcu SET TenDC = dungcu_TenDC, MaPh = dungcu_MaPh, SoLuong = dungcu_SoLuong WHERE MaDC = dungcu_MaDC;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Updated DungCu: ', dungcu_TenDC), NOW());
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE DeleteDungCu(
    IN dungcu_MaDC CHAR(10),
    IN maUser CHAR(10)
)
BEGIN
    DELETE FROM dungcu WHERE MaDC = dungcu_MaDC;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Deleted DungCu: ', dungcu_MaDC), NOW());
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE InsertHoaDon(
    IN hoadon_MaHD CHAR(10),
    IN hoadon_NgayLap DATE,
    IN hoadon_MaDC CHAR(10),
    IN hoadon_SoLuong INT,
    IN hoadon_ThanhTien INT,
    IN maUser CHAR(10)
)
BEGIN
    INSERT INTO hoadon (MaHD, NgayLap, MaDC, SoLuong, ThanhTien) VALUES (hoadon_MaHD, hoadon_NgayLap, hoadon_MaDC, hoadon_SoLuong, hoadon_ThanhTien);
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Inserted a new HoaDon: ', hoadon_MaHD), NOW());
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE UpdateHoaDon(
    IN hoadon_MaHD CHAR(10),
    IN hoadon_MaDC CHAR(10),
    IN hoadon_SoLuong INT,
    IN hoadon_ThanhTien INT,
    IN maUser CHAR(10)
)
BEGIN
    UPDATE hoadon SET MaDC = hoadon_MaDC, SoLuong = hoadon_SoLuong, ThanhTien = hoadon_ThanhTien WHERE MaHD = hoadon_MaHD;
    INSERT INTO log (MaUser, Noidung, ThoiGian) VALUES (maUser, CONCAT('Updated HoaDon: ', hoadon_MaHD), NOW());
END //
DELIMITER ;
DELIMITER //

CREATE PROCEDURE CountAndInsertTaiKhoan(
    IN p_MaUser VARCHAR(50),
    IN p_Username VARCHAR(50),
    IN p_Password VARCHAR(50),
    IN p_LoaiTK VARCHAR(50)
)
BEGIN
    DECLARE v_Count INT;
    DECLARE v_Success BIT DEFAULT 0;

    -- Count the number of rows in taikhoan
    SELECT COUNT(*) INTO v_Count FROM taikhoan;

    -- Check if user already exists
    IF NOT EXISTS (SELECT 1 FROM taikhoan WHERE Username = p_Username) THEN
        -- Insert new record into taikhoan
        INSERT INTO taikhoan (MaUser, Username, Password, LoaiTK)
        VALUES (p_MaUser, p_Username, p_Password, p_LoaiTK);
        SET v_Success = 1;
    END IF;

    SELECT v_Count AS count, v_Success AS success;
END//
CREATE TABLE HocSinh (
    MaHS CHAR(10) PRIMARY KEY,
    TenHS VARCHAR(50),
    MaUS CHAR(10),
    MaLop CHAR(10),
    NgaySinh DATE
);
DELIMITER //
CREATE PROCEDURE InsertHocSinh(
    IN _MaHS CHAR(10),
    IN _TenHS VARCHAR(50),
    IN _MaUS CHAR(10),
    IN _MaLop CHAR(10),
    IN _NgaySinh DATE,
    IN _maUser CHAR(10)
)
BEGIN
    INSERT INTO HocSinh (MaHS, TenHS, MaUS, MaLop, NgaySinh) 
    VALUES (_MaHS, _TenHS, _MaUS, _MaLop, _NgaySinh);
    
    INSERT INTO log (MaUser, Noidung, ThoiGian) 
    VALUES (_maUser, CONCAT('Inserted new HocSinh: ', _MaHS), NOW());
END //
DELIMITER ;
DELIMITER //
CREATE PROCEDURE UpdateHocSinh(
    IN _MaHS CHAR(10),
    IN _TenHS VARCHAR(50),
    IN _MaLop CHAR(10),
    IN _NgaySinh DATE,
    IN _maUser CHAR(10)
)
BEGIN
    UPDATE HocSinh 
    SET TenHS = _TenHS, MaLop = _MaLop, NgaySinh = _NgaySinh 
    WHERE MaHS = _MaHS;
    
    INSERT INTO log (MaUser, Noidung, ThoiGian) 
    VALUES (_maUser, CONCAT('Updated HocSinh: ', _MaHS), NOW());
END //
DELIMITER ;


-- Create table for DiemSo
CREATE TABLE DiemSo (
    MaHS CHAR(10),
    TenMon VARCHAR(50),
    Diem INT,
    HeSo INT,
    ThoiGian DATE
);

-- Procedure for inserting DiemSo
DELIMITER //
CREATE PROCEDURE InsertDiemSo(
    IN diemso_MaHS CHAR(10),
    IN diemso_TenMon VARCHAR(50),
    IN diemso_Diem INT,
    IN diemso_HeSo INT,
    IN diemso_ThoiGian DATE,
    IN maUser CHAR(10)
)
BEGIN
    INSERT INTO DiemSo (MaHS, TenMon, Diem, HeSo, ThoiGian) 
    VALUES (diemso_MaHS, diemso_TenMon, diemso_Diem, diemso_HeSo, diemso_ThoiGian);
    
    INSERT INTO log (MaUser, Noidung, ThoiGian) 
    VALUES (maUser, CONCAT('Inserted a new DiemSo for MaHS: ', diemso_MaHS), NOW());
END //
DELIMITER ;

-- Procedure for updating DiemSo
DELIMITER //
CREATE PROCEDURE UpdateDiemSo(
    IN diemso_MaHS CHAR(10),
    IN diemso_TenMon VARCHAR(50),
    IN diemso_Diem INT,
    IN diemso_HeSo INT,
    IN diemso_ThoiGian DATE,
    IN maUser CHAR(10)
)
BEGIN
    UPDATE DiemSo 
    SET TenMon = diemso_TenMon, Diem = diemso_Diem, HeSo = diemso_HeSo, ThoiGian = diemso_ThoiGian 
    WHERE MaHS = diemso_MaHS;
    
    INSERT INTO log (MaUser, Noidung, ThoiGian) 
    VALUES (maUser, CONCAT('Updated DiemSo for MaHS: ', diemso_MaHS), NOW());
END //
DELIMITER ;


