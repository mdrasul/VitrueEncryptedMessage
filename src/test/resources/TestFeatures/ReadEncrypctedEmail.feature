Feature: Reading Virtue Encrypcted Email
  As a Potential Receipant of Virtue Encrypted Email 
  I can Decrypcted and read Email

	@smoke
  Scenario: Receipant Can Decrypte and Read Email
    Given I Login to my Email Account
    And I See a Virtue Encrypted Email
    When I Decrypted the Email
    Then I Validate I can Read VirtruDecryptedEmail Content
    #And I Switch Back to My Gmail Account
