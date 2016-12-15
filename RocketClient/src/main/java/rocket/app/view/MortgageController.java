package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private ComboBox cmbTerm;
	@FXML
	private Button calculate;

	@FXML
	private Label errorMessage;
	@FXML
	private Label creditScore;
	@FXML
	private Label Term;
	@FXML
	private Label Income;
	@FXML
	private Label Expenses;
	@FXML
	private Label houseCost;
	@FXML
	private Label lblMortgagePayment;
	@FXML
	private Label downPayment;

	
	
	

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;

		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();

		lq.setIncome(Double.parseDouble((txtIncome.getText())));
		lq.setiTerm(Integer.parseInt(cmbTerm.getPromptText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiDownPayment(Integer.parseInt(downPayment.getText()));
		lq.setExpenses(Double.parseDouble((txtIncome.getText())));

		if (cmbTerm.getValue() == "15 yrs") {
			lq.setiTerm(15 * 12);
		}

		else if (cmbTerm.getValue() == "30 yrs") {
			lq.setiTerm(30 * 12);
		}

		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq

		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {

		if (lRequest.getdPayment() <= 0.36 * (lRequest.getIncome() / 12)
				&& lRequest.getdPayment() <= 0.28 * (lRequest.getIncome() / 12 - lRequest.getExpenses())) {
			System.out.format("%.2f", lRequest.getdPayment());

		}

		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places

	}

}
