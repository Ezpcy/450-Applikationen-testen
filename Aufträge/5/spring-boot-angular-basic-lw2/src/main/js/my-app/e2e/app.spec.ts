import { test, expect } from '@playwright/test';

test('should display app title', async ({ page }) => {
  await page.goto('/');

  await expect(page).toHaveTitle('TBZ Students');
});

test('should display navigation buttons', async ({ page }) => {
  await page.goto('/');

  await expect(page.locator('a[routerLink="/students"]')).toContainText('List Students');
  await expect(page.locator('a[routerLink="/addstudents"]')).toContainText('Add Students');
});