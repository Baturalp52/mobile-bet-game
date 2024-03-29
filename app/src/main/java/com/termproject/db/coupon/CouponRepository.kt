package com.termproject.db.coupon

class CouponRepository(private val couponDAO: CouponDAO) {


    fun newCoupon(coupon: Coupon): Long {
        return couponDAO.newCoupon(coupon)
    }

    fun newPlayedBet(playedBet: PlayedBet): Long {
        return couponDAO.newPlayedBet(playedBet)
    }

    fun newTeam(team: Team) {
        couponDAO.newTeam(team)
    }

    fun newPlayedGame(playedGame: PlayedGame) {
        couponDAO.newPlayedGame(playedGame)
    }


    fun getCouponById(couponId: Long): CouponWithPlayedGames {
        return couponDAO.getCouponById(couponId)
    }

    fun getAllCoupons(): List<CouponWithPlayedGames> {
        return couponDAO.getAllCoupons()
    }

    fun getPendingCoupons(): List<CouponWithPlayedGames> {
        return couponDAO.getPendingCoupons()
    }

    fun updateBet(bet: PlayedBet) {
        couponDAO.updateBet(bet)
    }

    fun updateCoupon(coupon: Coupon) {
        couponDAO.updateCoupon(coupon)
    }

    fun getTeamByTeamId(teamId: Long): Team {
        return couponDAO.getTeamByTeamId(teamId)
    }

}