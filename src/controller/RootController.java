package controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import application.AppMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Member;
	
public class RootController implements Initializable { 
	//로그아웃창
	@FXML private ImageView t1Logout1;
	@FXML private ImageView t1Logout2;
	@FXML private ImageView t1Logout3;
	@FXML private ImageView t1Logout4;
	@FXML private ImageView t1Logout5;
	@FXML private ImageView t1Logout6;
	@FXML private ImageView t1Logout7;
	@FXML private ImageView t1Logout8;
	@FXML private ImageView t1Logout9;
	@FXML private ImageView t1Logout10;
	@FXML private ImageView t1Logout11;
	@FXML private ImageView t1Logout12;
	@FXML private ImageView t1Logout13;
	@FXML private ImageView t1Logout14;
	@FXML private ImageView t1Logout15;
	@FXML private ImageView t1Logout16;
	//로그인창
	@FXML private ImageView t1Logon1;
	@FXML private ImageView t1Logon2;
	@FXML private ImageView t1Logon3;
	@FXML private ImageView t1Logon4;
	@FXML private ImageView t1Logon5;
	@FXML private ImageView t1Logon6;
	@FXML private ImageView t1Logon7;
	@FXML private ImageView t1Logon8;
	@FXML private ImageView t1Logon9;
	@FXML private ImageView t1Logon10;
	@FXML private ImageView t1Logon11;
	@FXML private ImageView t1Logon12;
	@FXML private ImageView t1Logon13;
	@FXML private ImageView t1Logon14;
	@FXML private ImageView t1Logon15;
	@FXML private ImageView t1Logon16;
	////////////////////////////테이블 컬럼 FXML
	private ObservableList<Member> t2TableViewList = FXCollections.observableArrayList();
	private ArrayList<Member> dbt2TableViewList = new ArrayList<>();
	@FXML private TableView<Member> t2TableView; //t2테이블뷰 
	@FXML private TableColumn<Member, String>t2name;;
	@FXML private TableColumn<Member, String>t2Id;;
	@FXML private TableColumn<Member, String>t2Gender;;
	@FXML private TableColumn<Member, String>t2age;;
	@FXML private TableColumn<Member, String>t2PhoneNumber;;
	@FXML private TableColumn<Member, String>t2Email;;
	@FXML private TableColumn<Member, String>t2Date;;
	//프로그램종료버튼
	@FXML private  Button programExit;
	//Memo
	static TextArea  t1Memo;
	String memo;
	Scanner s;
	BufferedWriter bufferedWriter;
	@FXML private Button t1BtnMemo;
	//주문현황
	@FXML private Button t1BtnOrder;
	//관리자모드
	@FXML private Button t1BtnManager;
	//서버 
	private ServerSocket serverSocket;
	//로그인창
	private ImageView mlsAuto;
	private Button mlslog1;
	private Button mlslog2;
	private TextField mlsId;
	private TextField mlsPassword;
	private Button mlsExit;
	//스테이지
	private Stage stage;
	private Stage memoStage;
	//스테이지set
	public void setStage(Stage stage) {
		this.stage = stage;
	}
//============================================================================
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//서버
		serverControl();
		//로그인조절
		loinControl();
		//작업사항(메모장)
		t1BtnMemo.setOnAction(e->{try {memoControl();}catch(Exception e1) {}});
		//주문현현황
		t1BtnOrder.setOnAction(e->{t1BtnOrderButton();});
		//관리자실행
		t1BtnManager.setOnAction(e->{t1BtnManager();});
		//프로그램종료
		programExit.setOnAction(e->{AppMain.stage.close();});
		//테이블뷰 세팅
		//t2TableviewSet();
}
	//t2테이블뷰 세팅
	private void t2TableviewSet() {
		t2name.setCellValueFactory(new PropertyValueFactory<>("name"));
		t2name.setStyle("-fx-alignment:CENTER;");
		t2Id.setCellValueFactory(new PropertyValueFactory<>("id"));
		t2Id.setStyle("-fx-alignment:CENTER;");
		t2Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		t2Gender.setStyle("-fx-alignment:CENTER;");
		t2age.setCellValueFactory(new PropertyValueFactory<>("age"));
		t2age.setStyle("-fx-alignment:CENTER;");
		t2PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonNumber"));
		t2PhoneNumber.setStyle("-fx-alignment:CENTER;");
		t2Email.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
		t2Email.setStyle("-fx-alignment:CENTER;");
		t2Date.setCellValueFactory(new PropertyValueFactory<>("date"));
		t2Date.setStyle("-fx-alignment:CENTER;");
		
		t2TableView.setItems(t2TableViewList);
		dbt2TableViewList =MemberDAO.getMemberTotalData();
				for (Member member : dbt2TableViewList) {
					t2TableViewList.add(member);
				}
	}
	//주문현황
	private void t1BtnOrderButton() {
		Stage orderStage=new Stage(StageStyle.DECORATED);
		orderStage.setTitle("주문현황");
		orderStage.setX(250);
		orderStage.setY(130);
		orderStage.initModality(Modality.NONE);
		orderStage.initOwner(stage);
		Parent root =null;
		try{
			root=FXMLLoader.load(getClass().getResource("../view/t1BtnOrder.fxml"));
		}catch(Exception e) {
		}
		Button t1OrderReg =(Button)root.lookup("#t1OrderReg");
		Scene scene = new Scene(root);
		orderStage.setScene(scene);
		orderStage.show();
		t1OrderReg.setOnAction(e->{orderStage.close();});
}
	//서버컨트롤
	private void serverControl() {
		try {
			serverSocket =new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.0"
					+ ""
					+ ".181", 5000));
		} catch (IOException e) {
			callAlert("서버접속오류: 서버에 접속할 수 없습니다.");
			AppMain.stage.close();
			}
	}
	//로그인 컨트롤
	private void loinControl() {
		t1Logon1.setVisible(true);
		t1Logon2.setVisible(true);
		t1Logon3.setVisible(true);
		t1Logon4.setVisible(true);
		t1Logon5.setVisible(true);
		t1Logon6.setVisible(true);
		t1Logon7.setVisible(true);
		t1Logon8.setVisible(true);
		t1Logon9.setVisible(false);
		t1Logon10.setVisible(false);
		t1Logon11.setVisible(false);
		t1Logon12.setVisible(false);
		t1Logon12.setVisible(false);
		t1Logon13.setVisible(false);
		t1Logon14.setVisible(false);
		t1Logon15.setVisible(false);
		t1Logon16.setVisible(false);
	}
	//작업사항(메모장)
	private void memoControl() {
		Stage memoStage=new Stage(StageStyle.DECORATED);
		memoStage.setTitle("작업사항");
		memoStage.setX(600);
		memoStage.setY(76);
		memoStage.initModality(Modality.NONE);
		memoStage.initOwner(stage);
		Parent root =null; 
		try {
			root=FXMLLoader.load(getClass().getResource("../view/memo.fxml"));
			s= new Scanner(new BufferedReader(new FileReader("memo.txt")));
		} catch (IOException e) {
			createMemo();
		}finally {
			try {
				s= new Scanner(new BufferedReader(new FileReader("memo.txt")));
			} catch (Exception e) {
				callAlert("파일 생성.:작업 공간이 생성되었습니다.\n*작업사항*을 다시 눌러주세요");
			}
		}
		
		t1Memo=(TextArea)root.lookup("#t1Memo");
		Button t1MemoBtnSave=(Button)root.lookup("#t1MemoBtnSave");
		Button t1MemoBtnCancel=(Button)root.lookup("#t1MemoBtnCancel");
		while(s.hasNextInt()) {
			memo=s.nextLine();
			
		}
		t1Memo.setText(memo);
		//System.out.println("1 memo:"+memo);
		
		Scene scene = new Scene(root);
		memoStage.setScene(scene);
		memoStage.show();
		
		
		//작업사항(메모장)저장
		t1MemoBtnSave.setOnAction(e->{
			t1MemoBtnCancel.setOnAction(e1->memoStage.close());
		    createMemo();
		    memoStage.close();
		});
		//작업사항(메모장)닫기
		t1MemoBtnCancel.setOnAction(e->{memoStage.close();
		});
	}
	//알림창 함수
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("알림창");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring( contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
	//파일생성
	private void createMemo() {
		    try {
		    	memo =t1Memo.getText();
		    	//System.out.println("2 memo:"+memo);
		        bufferedWriter = new BufferedWriter(new FileWriter("memo.txt"));
		        bufferedWriter.write(memo);
		        bufferedWriter.flush();
		        bufferedWriter.close();
		    }catch(NullPointerException n) {
		    } catch (IOException e) {
		        // 파일 입출력 예외에 대한 코드는 여기에 작성하세요.
		        System.out.println("memo.txt 파일을 열 수 없습니다.");
		    }
	}
	//관리자실행
	private void t1BtnManager() {
		memoStage=new Stage(StageStyle.UNDECORATED);
		memoStage.setTitle("작업사항");
		memoStage.setX(250);
		memoStage.setY(300);
		memoStage.initModality(Modality.NONE);
		memoStage.initOwner(stage);
		Parent root =null;
		try {
			root=FXMLLoader.load(getClass().getResource("../view/managerLoginStage.fxml"));
		} catch (IOException e) {}
		ImageView mlsAuto=(ImageView)root.lookup("#mlsAuto");
		mlslog1=(Button)root.lookup("#mlslog1");
		mlslog2=(Button)root.lookup("#mlslog2");
		mlsId=(TextField)root.lookup("#mlsId");
	    mlsPassword=(TextField)root.lookup("#mlsPassword");
		mlsExit=(Button)root.lookup("#mlsExit");
		Scene scene = new Scene(root);
		memoStage.setScene(scene);
		memoStage.show();
		mlsExit.setOnAction(e->{memoStage.close();});
		mlslog1.setOnAction(e->{loginControl();});//로그인컨트롤
		mlslog2.setOnAction(e->{loginControl();});//로그인컨트롤
	}
	//로그인 컨트롤
	private void loginControl() {
		if(!(mlsId.getText().equals("root")&&mlsPassword.getText().equals("123456"))) {
			callAlert("로그인실패:아이디나 패스워드가 맞지 않습니다.");
			mlsId.clear();mlsId.clear();
			return;
		}else {
		try {
		Stage stockmanagement =new Stage();
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/stockmanagement.fxml"));
		Parent root1=loader.load();
		Scene scene1=new Scene(root1);
		stockmanagement.setScene(scene1);
		memoStage.close();
		stockmanagement.show();
		callAlert("화면전환 성공:메인 화면으로 전환되었습니다. ");
		} catch (Exception e1) {
			callAlert("화면전환오류:화면전환 실패");
		}
		}
	}
}