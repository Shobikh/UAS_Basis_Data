-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 21 Feb 2024 pada 14.53
-- Versi server: 10.11.6-MariaDB-2
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kelontong`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `tambah_karyawan` (IN `nama_karyawan` VARCHAR(100), IN `password_plain` VARCHAR(255), IN `tanggal` VARCHAR(100))   BEGIN
    DECLARE next_id INT;
    DECLARE prefix CHAR(3);
    DECLARE new_id VARCHAR(10);
    DECLARE encrypted_password VARCHAR(255);
    DECLARE tanggal_reg DATE;
    
    -- set tanggal
    SET tanggal_reg = tanggal;

    -- Ambil ID terakhir dari tabel karyawan
    SELECT MAX(CAST(SUBSTRING(id_karyawan, 4) AS UNSIGNED)) INTO next_id FROM karyawan;
    IF next_id IS NULL THEN
        SET next_id = 0;
    END IF;

    -- Tentukan prefix untuk id_karyawan
    SET prefix = 'KAR';

    -- Format nilai id_karyawan berikutnya
    SET new_id = CONCAT(prefix, LPAD(next_id + 1, 4, '0'));

    -- Enkripsi password menggunakan fungsi PASSWORD()
    SET encrypted_password = PASSWORD(password_plain);

    -- Masukkan data baru ke dalam tabel karyawan
    INSERT INTO karyawan (id_karyawan, nama_karyawan, password, tanggal_daftar) VALUES (new_id, nama_karyawan, encrypted_password, tanggal);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_admin` varchar(7) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_admin`, `password`) VALUES
('ADM0001', '*4ACFE3202A5FF5CF467898FC58AAB1D615029441');

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(11) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga`, `stok`, `tanggal_masuk`) VALUES
('BRG00001', 'Aqua Sedang', 4000, 1000, '2024-02-21'),
('BRG00002', 'Aqua Besar', 10000, 100, '2024-02-21'),
('BRG00003', 'Teh Javana', 3000, 100, '2024-02-21'),
('BRG00004', 'A&W Sarsaparilla', 7000, 99, '2024-02-21'),
('BRG00005', 'Aoka Keju', 3000, 100, '2024-02-21'),
('BRG00006', 'Aoka Coklat', 3000, 98, '2024-02-21');

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail` varchar(12) NOT NULL,
  `id_transaksi` varchar(11) NOT NULL,
  `id_barang` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail`, `id_transaksi`, `id_barang`, `jumlah`, `subtotal`) VALUES
('TRXD0001', 'TRX0001', 'BRG00004', 1, 7000),
('TRXD0002', 'TRX0001', 'BRG00006', 2, 6000);

--
-- Trigger `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `before_insert_detail_transaksi` BEFORE INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    DECLARE next_id INT;
    DECLARE new_id VARCHAR(10);

    -- Ambil ID terakhir dari tabel detail_transaksi
    SELECT MAX(CAST(SUBSTRING(id_detail, 5) AS UNSIGNED)) INTO next_id FROM detail_transaksi;
    IF next_id IS NULL THEN
        SET next_id = 0;
    END IF;

    -- Format nilai id_detail berikutnya
    SET new_id = CONCAT('TRXD', LPAD(next_id + 1, 4, '0'));

    -- Set nilai id_detail baru
    SET NEW.id_detail = new_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_stok_barang` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
    DECLARE stok_saat_ini INT;
    
    -- Mengambil stok saat ini dari tabel barang
    SELECT stok INTO stok_saat_ini
    FROM barang
    WHERE id_barang = NEW.id_barang;
    
    -- Mengurangi stok barang berdasarkan jumlah yang dibeli
    UPDATE barang
    SET stok = stok_saat_ini - NEW.jumlah
    WHERE id_barang = NEW.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` varchar(11) NOT NULL,
  `nama_karyawan` varchar(100) NOT NULL,
  `password` text NOT NULL,
  `tanggal_daftar` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama_karyawan`, `password`, `tanggal_daftar`) VALUES
('KAR0001', 'MUKHAMMAD SHOBIKH', '*783C93B02229E6F96213CBEA11694DDE87AEBE2B', '2024-02-21');

--
-- Trigger `karyawan`
--
DELIMITER $$
CREATE TRIGGER `before_insert_nama_karyawan` BEFORE INSERT ON `karyawan` FOR EACH ROW BEGIN
    SET NEW.nama_karyawan = UPPER(NEW.nama_karyawan);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `nota_transaksi`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `nota_transaksi` (
`id_transaksi` varchar(11)
,`waktu` date
,`nama_barang` varchar(100)
,`harga` int(11)
,`jumlah` int(11)
,`subtotal` int(11)
,`bayar` int(11)
,`kembali` int(11)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(11) NOT NULL,
  `waktu` date NOT NULL DEFAULT current_timestamp(),
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `waktu`, `total`, `bayar`, `kembali`) VALUES
('TRX0001', '2024-02-21', 13000, 15000, 2000);

-- --------------------------------------------------------

--
-- Struktur untuk view `nota_transaksi`
--
DROP TABLE IF EXISTS `nota_transaksi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `nota_transaksi`  AS SELECT `t`.`id_transaksi` AS `id_transaksi`, `t`.`waktu` AS `waktu`, `b`.`nama_barang` AS `nama_barang`, `b`.`harga` AS `harga`, `dt`.`jumlah` AS `jumlah`, `dt`.`subtotal` AS `subtotal`, `t`.`bayar` AS `bayar`, `t`.`kembali` AS `kembali` FROM ((`transaksi` `t` join `detail_transaksi` `dt` on(`t`.`id_transaksi` = `dt`.`id_transaksi`)) join `barang` `b` on(`dt`.`id_barang` = `b`.`id_barang`)) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_transaksi` (`id_transaksi`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `detail_transaksi_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `detail_transaksi_ibfk_2` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
