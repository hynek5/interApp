package com.microfocus.interApp;

import com.microfocus.interApp.domain.MailAccount;
import com.microfocus.interApp.domain.Task;
import com.microfocus.interApp.tasks.DefaultCriteria;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class InterAppApplicationTests {

	DefaultCriteria criteria = new DefaultCriteria();

	@Test
	void contextLoads() {
	}

	@Test
	public void compareMailAcc() {
		MailAccount account1 = new MailAccount("hrabik.h");
		MailAccount account2 = new MailAccount("hrabik.h");
		System.out.println(account1.equals(account2));

		Set<MailAccount> mailAccounts = new HashSet<>();
		mailAccounts.add(account1);
		System.out.println(mailAccounts.add(account2));

	}

	@Test
	public void defCriteriaTest() {

		boolean[] key;
		//run task
		Task task1 = new Task();
		task1.setGid("G1");
		task1.setType(Task.OperType.READ);

		key = criteria.generateKey(task1);

		Assert.assertTrue(criteria.compareKeys(key));

	}

	@Test(priority = 1)
	public void defCriteriaTest2() {

		boolean[] key;
		//do not run task - the same group
		Task task1 = new Task();
		task1.setGid("G1");
		task1.setType(Task.OperType.READ);

		key = criteria.generateKey(task1);;

		Assert.assertFalse(criteria.compareKeys(key));

	}


}
