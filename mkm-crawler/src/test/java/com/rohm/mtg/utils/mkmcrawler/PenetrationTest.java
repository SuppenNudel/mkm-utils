package com.rohm.mtg.utils.mkmcrawler;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.rohm.mtg.utils.mkmcrawler.model.MkmCardInfo;

public class PenetrationTest {

	@Disabled
	@Test
	public void alot() {
		System.out.println("PenetrationTest.alot()");
		List<Thread> threads = new ArrayList<>();
		for(int i=0;i<100;++i) {
			Thread thread = new Thread(() -> {
				MkmCardInfo mkmCardInfo = MkmCrawler.card("Boseiju, Who Endures").get();
				System.out.println(mkmCardInfo);
			});
			threads.add(thread);
		}
		threads.forEach(Thread::start);
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}
