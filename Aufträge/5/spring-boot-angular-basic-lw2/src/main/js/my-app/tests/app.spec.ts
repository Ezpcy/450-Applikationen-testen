import { expect, test } from "@playwright/test";

test("Page loads", async ({ page }) => {
  await page.goto("/");
  await expect(page.locator('h1')).toBeVisible();
});

test("Navigate to student list", async ({ page }) => {
  await page.goto("/");
  await page.click('a[href="/students"]');
  await expect(page).toHaveURL(/.*\/students/);
  await expect(page.locator('h2')).toContainText('Student List');
});

test("Navigate to add student form", async ({ page }) => {
  await page.goto("/students");
  await page.click('a[href="/addstudent"]');
  await expect(page).toHaveURL(/.*\/addstudent/);
  await expect(page.locator('h2')).toContainText('Add Student');
});

test("Add a new student successfully", async ({ page }) => {
  await page.goto("/addstudent");

  await page.fill('input[name="name"]', 'John Doe');
  await page.fill('input[name="email"]', 'john.doe@example.com');

  await page.click('button[type="submit"]');

  await expect(page).toHaveURL(/.*\/students/);
  await expect(page.locator('.student-list')).toContainText('John Doe');
});

test("Form validation - empty fields", async ({ page }) => {
  await page.goto("/addstudent");

  await page.click('button[type="submit"]');

  const nameField = page.locator('input[name="name"]');
  const emailField = page.locator('input[name="email"]');

  await expect(nameField).toBeInvalid();
  await expect(emailField).toBeInvalid();
});

test("Form validation - invalid email format", async ({ page }) => {
  await page.goto("/addstudent");

  await page.fill('input[name="name"]', 'John Doe');
  await page.fill('input[name="email"]', 'invalid-email');

  await page.click('button[type="submit"]');

  const emailField = page.locator('input[name="email"]');
  await expect(emailField).toBeInvalid();
});

test("Navigation flow - add student and return to list", async ({ page }) => {
  await page.goto("/");

  await page.click('a[href="/students"]');
  await expect(page).toHaveURL(/.*\/students/);

  await page.click('a[href="/addstudent"]');
  await expect(page).toHaveURL(/.*\/addstudent/);

  await page.fill('input[name="name"]', 'Jane Smith');
  await page.fill('input[name="email"]', 'jane.smith@example.com');

  await page.click('button[type="submit"]');
  await expect(page).toHaveURL(/.*\/students/);

  await expect(page.locator('.student-list')).toContainText('Jane Smith');
});

test("Cancel adding student and return to list", async ({ page }) => {
  await page.goto("/addstudent");

  await page.fill('input[name="name"]', 'Test User');
  await page.fill('input[name="email"]', 'test@example.com');

  await page.click('button:has-text("List")');

  await expect(page).toHaveURL(/.*\/students/);
});

test("Student list displays correctly", async ({ page }) => {
  await page.goto("/students");

  await expect(page.locator('h2')).toContainText('Student List');
  await expect(page.locator('a[href="/addstudent"]')).toBeVisible();

  const studentTable = page.locator('.student-table');
  if (await studentTable.isVisible()) {
    await expect(studentTable.locator('th')).toContainText(['Name', 'Email']);
  }
});

test("Handle special characters in student name", async ({ page }) => {
  await page.goto("/addstudent");

  await page.fill('input[name="name"]', 'José María-González');
  await page.fill('input[name="email"]', 'jose.maria@example.com');

  await page.click('button[type="submit"]');

  await expect(page).toHaveURL(/.*\/students/);
  await expect(page.locator('.student-list')).toContainText('José María-González');
});

test("Handle long student name", async ({ page }) => {
  await page.goto("/addstudent");

  const longName = 'A'.repeat(100);
  await page.fill('input[name="name"]', longName);
  await page.fill('input[name="email"]', 'long.name@example.com');

  await page.click('button[type="submit"]');

  await expect(page).toHaveURL(/.*\/students/);
});

test("Application header navigation", async ({ page }) => {
  await page.goto("/");

  await expect(page.locator('nav')).toBeVisible();
  await expect(page.locator('a[href="/"]')).toContainText('Home');
  await expect(page.locator('a[href="/students"]')).toContainText('Students');
});

test("Responsive design - mobile view", async ({ page }) => {
  await page.setViewportSize({ width: 375, height: 667 });
  await page.goto("/");

  await expect(page.locator('h1')).toBeVisible();

  await page.goto("/students");
  await expect(page.locator('h2')).toBeVisible();

  await page.goto("/addstudent");
  await expect(page.locator('form')).toBeVisible();
});
