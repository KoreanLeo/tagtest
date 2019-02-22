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
	//�α׾ƿ�â
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
	//�α���â
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
	////////////////////////////���̺� �÷� FXML
	private ObservableList<Member> t2TableViewList = FXCollections.observableArrayList();
	private ArrayList<Member> dbt2TableViewList = new ArrayList<>();
	@FXML private TableView<Member> t2TableView; //t2���̺�� 
	@FXML private TableColumn<Member, String>t2name;;
	@FXML private TableColumn<Member, String>t2Id;;
	@FXML private TableColumn<Member, String>t2Gender;;
	@FXML private TableColumn<Member, String>t2age;;
	@FXML private TableColumn<Member, String>t2PhoneNumber;;
	@FXML private TableColumn<Member, String>t2Email;;
	@FXML private TableColumn<Member, String>t2Date;;
	//���α׷������ư
	@FXML private  Button programExit;
	//Memo
	static TextArea  t1Memo;
	String memo;
	Scanner s;
	BufferedWriter bufferedWriter;
	@FXML private Button t1BtnMemo;
	//�ֹ���Ȳ
	@FXML private Button t1BtnOrder;
	//�����ڸ��
	@FXML private Button t1BtnManager;
	//���� 
	private ServerSocket serverSocket;
	//�α���â
	private ImageView mlsAuto;
	private Button mlslog1;
	private Button mlslog2;
	private TextField mlsId;
	private TextField mlsPassword;
	private Button mlsExit;
	//��������
	private Stage stage;
	private Stage memoStage;
	//��������set
	public void setStage(Stage stage) {
		this.stage = stage;
	}
//============================================================================
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//����
		serverControl();
		//�α�������
		loinControl();
		//�۾�����(�޸���)
		t1BtnMemo.setOnAction(e->{try {memoControl();}catch(Exception e1) {}});
		//�ֹ�����Ȳ
		t1BtnOrder.setOnAction(e->{t1BtnOrderButton();});
		//�����ڽ���
		t1BtnManager.setOnAction(e->{t1BtnManager();});
		//���α׷�����
		programExit.setOnAction(e->{AppMain.stage.close();});
		//���̺�� ����
		//t2TableviewSet();
}
	//t2���̺�� ����
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
	//�ֹ���Ȳ
	private void t1BtnOrderButton() {
		Stage orderStage=new Stage(StageStyle.DECORATED);
		orderStage.setTitle("�ֹ���Ȳ");
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
	//������Ʈ��
	private void serverControl() {
		try {
			serverSocket =new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.0"
					+ ""
					+ ".181", 5000));
		} catch (IOException e) {
			callAlert("�������ӿ���: ������ ������ �� �����ϴ�.");
			AppMain.stage.close();
			}
	}
	//�α��� ��Ʈ��
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
	//�۾�����(�޸���)
	private void memoControl() {
		Stage memoStage=new Stage(StageStyle.DECORATED);
		memoStage.setTitle("�۾�����");
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
				callAlert("���� ����.:�۾� ������ �����Ǿ����ϴ�.\n*�۾�����*�� �ٽ� �����ּ���");
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
		
		
		//�۾�����(�޸���)����
		t1MemoBtnSave.setOnAction(e->{
			t1MemoBtnCancel.setOnAction(e1->memoStage.close());
		    createMemo();
		    memoStage.close();
		});
		//�۾�����(�޸���)�ݱ�
		t1MemoBtnCancel.setOnAction(e->{memoStage.close();
		});
	}
	//�˸�â �Լ�
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("�˸�â");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring( contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
	//���ϻ���
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
		        // ���� ����� ���ܿ� ���� �ڵ�� ���⿡ �ۼ��ϼ���.
		        System.out.println("memo.txt ������ �� �� �����ϴ�.");
		    }
	}
	//�����ڽ���
	private void t1BtnManager() {
		memoStage=new Stage(StageStyle.UNDECORATED);
		memoStage.setTitle("�۾�����");
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
		mlslog1.setOnAction(e->{loginControl();});//�α�����Ʈ��
		mlslog2.setOnAction(e->{loginControl();});//�α�����Ʈ��
	}
	//�α��� ��Ʈ��
	private void loginControl() {
		if(!(mlsId.getText().equals("root")&&mlsPassword.getText().equals("123456"))) {
			callAlert("�α��ν���:���̵� �н����尡 ���� �ʽ��ϴ�.");
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
		callAlert("ȭ����ȯ ����:���� ȭ������ ��ȯ�Ǿ����ϴ�. ");
		} catch (Exception e1) {
			callAlert("ȭ����ȯ����:ȭ����ȯ ����");
		}
		}
	}
}