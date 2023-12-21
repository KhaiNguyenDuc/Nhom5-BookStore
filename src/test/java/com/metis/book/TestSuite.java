package com.metis.book;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AddressServiceTest.class,
        CartItemServiceTest.class,
        BookServiceTest.class,
        LanguageServiceTest.class,
        AimServiceImplTest.class,
        BlogServiceImplTest.class,
        CartItemServiceTest.class,
        CategoryServiceImplTest.class,
        CustomUserServiceImplTest.class
// Thêm các lớp kiểm thử khác nếu cần
})
public class TestSuite {
    // Không cần thêm bất kỳ nội dung nào ở đây
}
