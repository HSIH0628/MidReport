package tw.hsih.midreport.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import tw.hsih.midreport.bean.Member;
import tw.hsih.midreport.dao.DataImportDAO;
import tw.hsih.midreport.dao.DownloadDAO;
import tw.hsih.midreport.dao.MemberDAO;
import tw.hsih.midreport.util.ConnectionFactory;

public class Action {

	public static void main(String[] args) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			MemberDAO memberDAO = new MemberDAO(conn);

			Scanner scan = new Scanner(System.in);

			while (true) {
				System.out.println("請選擇功能：");
				System.out.println("(0) 退出程式");
				System.out.println("(1) 把Data資料下載下來");
				System.out.println("(2) 把資料放進資料庫裡");
				System.out.println("(3) 根據ID序號尋找公司");
				System.out.println("(4) 列出所有公司");
				System.out.println("(5) 新增資料公司資料");
				System.out.println("(6) 刪除特定資料");
				System.out.println("(7) 修改特定資料");
				int userInput = scan.nextInt();

				if (userInput == 0) {
					break;
				}
				if (userInput == 1) {
					DownloadDAO downloadDAO = new DownloadDAO();
					try {
						// 提供實際的檔案URL、目的地資料夾和檔案名稱
						String fileUrl = "https://opendataap2.e-land.gov.tw/./resource/files/2023-07-28/977f293a8b777a9be4e803f1ffb81827.csv";
						String destinationFolder = "C:\\Temp\\";
						String fileName = "MidData.csv";

						// 使用 downloadFile 方法
						downloadDAO.downloadFile(fileUrl, destinationFolder, fileName);
						System.out.println("檔案下載完成");
					} catch (IOException e) {
						System.out.println("Error downloading file: " + e.getMessage());
					}
				}
				if (userInput == 2) {
					DataImportDAO dataImportDAO = new DataImportDAO();
					try {
						// 提供實際的CSV文件路徑
						String csvFilePath = "C:\\Temp\\MidData.csv";

						// 調用 importData 方法
						dataImportDAO.insertData(conn, csvFilePath);
						System.out.println("資料放進去完成");
					} catch (Exception e) {
						System.out.println("Error importing data: " + e.getMessage());
					}
				}
				if (userInput == 3) {
					System.out.println("請輸入ID序號：");
					int id = scan.nextInt();
					Member member = memberDAO.findByID(id);
					System.out.println(member);
				}
				if (userInput == 4) {
					List<Member> members = memberDAO.findAll();
					if (members.isEmpty()) {
						System.out.println("目前沒有公司資料。");
					} else {
						System.out.println("所有公司資料：");
						for (Member member : members) {
							System.out.println(member);
							System.out.println(); // 換行
						}
					}
				}

				if (userInput == 5) {
					System.out.println("請輸入公司名稱：");
					String CompanyName = scan.next();
					System.out.println("請輸入地址：");
					String Address = scan.next();
					System.out.println("請輸入電話：");
					String Phone = scan.next();
					Member m = new Member(CompanyName, Address, Phone);

					memberDAO.createMember(m);
				}
				if (userInput == 6) {
					// 刪除特定資料
					System.out.println("請輸入要刪除的公司ID：");
					int memberId = scan.nextInt();

					// 使用 deleteMember 方法
					try {
						memberDAO.deleteMember(memberId);
						System.out.println("公司已成功刪除。");
					} catch (SQLException e) {
						System.out.println("刪除公司失敗，請檢查輸入的公司ID是否正確。");
						e.printStackTrace();
					}
				}
				if (userInput == 7) {
				    System.out.println("請輸入要修改的公司ID：");
				    int modifyId = scan.nextInt();

				    // 檢查該 ID 是否存在
				    Member existingMember = memberDAO.findByID(modifyId);
				    if (existingMember != null) {
				        // ID 存在，允許修改
				        System.out.println("請輸入新的公司名稱：");
				        String newCompanyName = scan.next();
				        System.out.println("請輸入新的地址：");
				        String newAddress = scan.next();
				        System.out.println("請輸入新的電話：");
				        String newPhone = scan.next();

				        // 更新 Member 物件資料
				        existingMember.setCompanyName(newCompanyName);
				        existingMember.setAddress(newAddress);
				        existingMember.setPhone(newPhone);

				        // 調用 updateMember 方法更新資料庫中的資料
				        try {
				            memberDAO.updateMember(existingMember);
				            System.out.println("公司資料已成功修改。");
				        } catch (SQLException e) {
				            System.out.println("修改公司資料失敗，請檢查輸入的資料是否正確。");
				            e.printStackTrace();
				        }
				    } else {
				        // ID 不存在，顯示錯誤訊息
				        System.out.println("指定的公司ID不存在，請檢查輸入的ID是否正確。");
				    }
				}

				for (int i = 1; i <= 7; i++) {
					System.out.println();
				}
			}

			scan.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("系統結束");
	}
}
