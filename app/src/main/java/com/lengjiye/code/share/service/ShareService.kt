package com.lengjiye.code.share.service

import com.lengjiye.code.constant.ServerConstants
import com.lengjiye.code.home.bean.ArticleBean
import com.lengjiye.code.home.bean.HomeBean
import com.lengjiye.code.share.bean.ShareBean
import com.lengjiye.network.BaseHttpResult
import io.reactivex.Observable
import org.intellij.lang.annotations.PrintFormat
import retrofit2.http.*

/**
 * @Author: lz
 * @Date: 2019-12-27
 * @Description:
 */
interface ShareService {

    // 广场
    @GET(ServerConstants.USER_ARTICLE_LIST)
    fun getUserArticleList(@Path("page") page: Int): Observable<BaseHttpResult<ArticleBean>>

    // 分享的数据（查看别人的）
    @GET(ServerConstants.USER_SHARE_ARTICLES)
    fun collectDeleteWebsite(@Path("user_id") userId: Int, @Path("page") page: Int): Observable<BaseHttpResult<ShareBean>>

    // 自己的分享列表
    @GET(ServerConstants.USER_PRIVATE_ARTICLES)
    fun getUserPrivateArticles(@Path("page") page: Int): Observable<BaseHttpResult<ShareBean>>

    // 删除分享
    @POST(ServerConstants.USER_ARTICLE_DELETE)
    @FormUrlEncoded
    fun userArticleDelete(@Path("article_id") articleId: Int): Observable<BaseHttpResult<String>>

    // 添加分享
    @POST(ServerConstants.USER_ARTICLE_ADD)
    @FormUrlEncoded
    fun userArticleAdd(@Field("title") title: String, @Field("link") link: String): Observable<BaseHttpResult<String>>

}